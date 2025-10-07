package com.restaurant.order.order_service.model.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class MenuItemResponse {
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private ItemCategory category;
}
