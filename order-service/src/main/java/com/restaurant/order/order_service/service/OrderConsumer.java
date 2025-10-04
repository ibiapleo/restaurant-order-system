package com.restaurant.order.order_service.service;

import com.restaurant.order.order_service.model.dtos.orders.OrderResponseDTO;
import com.restaurant.order.order_service.model.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "orders.completed", groupId = "order-group", containerFactory = "orderContainerFactory")
    public void consumeCompleted(OrderResponseDTO event) {
        System.out.println("📦 Pedido finalizado pela cozinha: " + event.getId());

        orderService.updateOrder(event, OrderStatus.COMPLETED);
    }
}
