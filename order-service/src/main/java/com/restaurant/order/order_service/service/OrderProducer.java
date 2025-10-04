package com.restaurant.order.order_service.service;

import com.restaurant.order.order_service.model.dtos.orders.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderResponseDTO> kafkaTemplate;

    public void publishOrderCreated(OrderResponseDTO event) {
        kafkaTemplate.send("orders.created", event.getId().toString(), event);
        System.out.println("🛒 Pedido publicado: " + event);
    }
}
