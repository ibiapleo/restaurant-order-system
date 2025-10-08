package com.restaurant.order.order_service.service;

import com.restaurant.order.order_service.model.dtos.orders.OrderResponseDTO;
import com.restaurant.order.order_service.model.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.time.LocalTime.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "orders.completed", groupId = "order-group", containerFactory = "orderContainerFactory")
    public void consumeCompleted(OrderResponseDTO event) {
        log.info("🍽️ [{}] Pedido finalizado pela cozinha: : {}", now(), event.getId());

        orderService.updateOrder(event, OrderStatus.COMPLETED);
    }
}
