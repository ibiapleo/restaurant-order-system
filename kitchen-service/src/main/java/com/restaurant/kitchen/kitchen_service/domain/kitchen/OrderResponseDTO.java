package com.restaurant.kitchen.kitchen_service.domain.kitchen;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderResponseDTO {
    private UUID id;
    private UUID customerId;
    private UUID tableId;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private Double totalAmount;
    private List<OrderItemResponseDTO> items;
}
