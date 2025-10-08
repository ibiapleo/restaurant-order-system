package com.restaurant.order.order_service.service;

import com.restaurant.order.order_service.model.dtos.orders.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static java.time.LocalTime.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderResponseDTO> kafkaTemplate;

    public void publishOrderCreated(OrderResponseDTO event) {
        kafkaTemplate.send("orders.created", event.getId().toString(), event);
        log.info("🍽️ [{}] Pedido criado e publicado: : {}", now(), event.getId());
    }
}
