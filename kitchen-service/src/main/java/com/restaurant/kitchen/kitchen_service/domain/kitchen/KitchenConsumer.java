package com.restaurant.kitchen.kitchen_service.domain.kitchen;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KitchenConsumer {

    private final KafkaTemplate<String, OrderResponseDTO> kafkaTemplate;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @KafkaListener(topics = "orders.created", groupId = "kitchen-group", containerFactory = "orderContainerFactory")
    public void consume(OrderResponseDTO event) {
        log.info("🍽️ [{}] Pedido recebido pela cozinha: {}", now(), event.getId());
        executor.submit(() -> processOrder(event));
    }

    private void processOrder(OrderResponseDTO event) {
        try {
            log.info("👨‍🍳 [{}] Iniciando preparo do pedido {}", now(), event.getId());
            event.setStatus(OrderStatus.IN_PREPARATION);

            // Simula tempo de preparo aleatório
            Thread.sleep(5000 + (long) (Math.random() * 5000));

            event.setStatus(OrderStatus.COMPLETED);
            kafkaTemplate.send("orders.completed", event.getId().toString(), event);
            log.info("✅ [{}] Pedido finalizado: {}", now(), event.getId());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("❌ [{}] Preparo do pedido {} interrompido", now(), event.getId(), e);
        }
    }

    private String now() {
        return LocalDateTime.now().format(formatter);
    }
}
