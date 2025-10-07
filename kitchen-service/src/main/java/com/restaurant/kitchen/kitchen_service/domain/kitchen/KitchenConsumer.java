package com.restaurant.kitchen.kitchen_service.domain.kitchen;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class KitchenConsumer {

    private final KafkaTemplate<String, OrderResponseDTO> kafkaTemplate;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @KafkaListener(topics = "orders.created", groupId = "kitchen-group", containerFactory = "orderContainerFactory")
    public void consume(OrderResponseDTO event) {
        System.out.println("🍽️ Pedido recebido pela cozinha: " + event.getId());
        executor.submit(() -> processOrder(event));
    }

    private void processOrder(OrderResponseDTO event) {
        try {
            System.out.println("👨‍🍳 Iniciando preparo do pedido " + event.getId());
            event.setStatus(OrderStatus.IN_PREPARATION);

            Thread.sleep(5000 + (long) (Math.random() * 5000));

            event.setStatus(OrderStatus.COMPLETED);
            kafkaTemplate.send("orders.completed", event.getId().toString(), event);
            System.out.println("✅ Pedido finalizado: " + event.getId());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}