package com.restaurant.order.order_service.service;

import com.restaurant.order.order_service.client.MenuServiceClient;
import com.restaurant.order.order_service.exceptions.CustomerNotFoundException;
import com.restaurant.order.order_service.exceptions.OrderNotFoundException;
import com.restaurant.order.order_service.exceptions.TableNotFoundException;
import com.restaurant.order.order_service.model.Customer;
import com.restaurant.order.order_service.model.Order;
import com.restaurant.order.order_service.model.OrderItem;
import com.restaurant.order.order_service.model.Table;
import com.restaurant.order.order_service.model.dtos.MenuItemResponse;
import com.restaurant.order.order_service.model.dtos.orders.OrderItemResponseDTO;
import com.restaurant.order.order_service.model.dtos.orders.OrderRequestDTO;
import com.restaurant.order.order_service.model.dtos.orders.OrderResponseDTO;
import com.restaurant.order.order_service.model.enums.OrderStatus;
import com.restaurant.order.order_service.repository.CustomerRepository;
import com.restaurant.order.order_service.repository.OrderRepository;
import com.restaurant.order.order_service.repository.TableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final TableRepository tableRepository;
    private final ModelMapper modelMapper;
    private final OrderProducer orderProducer;
    private final MenuServiceClient menuServiceClient;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {

        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(orderRequest.getCustomerId()));

        Table table = tableRepository.findById(orderRequest.getTableId())
                .orElseThrow(() -> new TableNotFoundException(orderRequest.getTableId()));

        Order order = new Order();
        order.setTable(table);
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = orderRequest.getItems().stream()
                .map(itemDTO -> {
                    OrderItem item = new OrderItem();
                    item.setMenuItemId(itemDTO.getMenuItemId());
                    item.setQuantity(itemDTO.getQuantity());
                    item.setOrder(order);

                    MenuItemResponse menuItem = menuServiceClient.getMenuItemById(itemDTO.getMenuItemId());
                    double subtotal = menuItem.getPrice() * itemDTO.getQuantity();

                    item.setSubtotal(subtotal);
                    return item;
                })
                .collect(Collectors.toList());

        order.setItems(items);

        double total = items.stream().mapToDouble(OrderItem::getSubtotal).sum();
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        OrderResponseDTO responseDTO = modelMapper.map(savedOrder, OrderResponseDTO.class);
        responseDTO.setItems(savedOrder.getItems().stream()
                .map(i -> modelMapper.map(i, OrderItemResponseDTO.class))
                .collect(Collectors.toList()));

        orderProducer.publishOrderCreated(responseDTO);

        return responseDTO;
    }

    public void updateOrderStatus(UUID orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.setStatus(status);
    }

    public OrderResponseDTO updateOrder(UUID orderId, OrderRequestDTO orderRequestDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if (orderRequestDTO.getCustomerId() != null) {
            order.setCustomer(modelMapper.map(orderRequestDTO.getCustomerId(), Customer.class));
        }
        if (orderRequestDTO.getTableId() != null) {
            order.setTable(modelMapper.map(orderRequestDTO.getTableId(), Table.class));
        }
        if (orderRequestDTO.getItems() != null) {
            order.getItems().clear();
            changeItems(orderRequestDTO, order);
        }

        return modelMapper.map(order, OrderResponseDTO.class);
    }

    public void updateOrder(OrderResponseDTO event, OrderStatus status) {
        orderRepository.updateOrder(event.getId(), status.name());
    }
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrderResponseDTO.class))
                .toList();
    }

    public OrderResponseDTO getOrderById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return modelMapper.map(order, OrderResponseDTO.class);
    }

    public void deleteOrder(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        orderRepository.delete(order);
    }

    private void changeItems(OrderRequestDTO orderRequestDTO, Order order) {
        List<OrderItem> items = orderRequestDTO.getItems().stream()
                .map(i -> {
                    OrderItem item = modelMapper.map(i, OrderItem.class);
                    item.setOrder(order);
                    item.setSubtotal(0.0); // TODO: calcular subtotal depois com MenuService
                    return item;
                }).collect(Collectors.toList());
        order.setItems(items);
        double total = items.stream().mapToDouble(OrderItem::getSubtotal).sum();
        order.setTotalAmount(total);
    }

}
