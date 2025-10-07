package com.restaurant.order.order_service.controller;

import com.restaurant.order.order_service.model.enums.OrderStatus;
import com.restaurant.order.order_service.model.dtos.orders.OrderRequestDTO;
import com.restaurant.order.order_service.model.dtos.orders.OrderResponseDTO;
import com.restaurant.order.order_service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
@Tag(name = "Orders", description = "Serviço de Pedidos - Gerenciamento de Pedidos")
@RequiredArgsConstructor
public class OrderController {

    public final OrderService orderService;

    @GetMapping
    @Operation(summary = "Listar todos os pedidos", description = "Retorna uma lista com todos os pedidos registrados")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar um pedido pelo id", description = "Retorna os metadados de um pedido")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    @Operation(summary = "Criar um pedido")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO order = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PatchMapping("/status/{id}")
    @Operation(summary = "Atualiza o status de um pedido")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable UUID id, @RequestBody OrderStatus orderStatus) {
        orderService.updateOrderStatus(id, orderStatus);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza todos os dados de um pedido")
    public ResponseEntity<OrderResponseDTO> updateOrder (@PathVariable UUID id, @RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO newOrder = orderService.updateOrder(id, orderRequestDTO);
        return ResponseEntity.ok(newOrder);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um pedido")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
