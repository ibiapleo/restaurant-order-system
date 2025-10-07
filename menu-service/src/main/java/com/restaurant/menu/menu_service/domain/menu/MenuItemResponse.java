package com.restaurant.menu.menu_service.domain.menu;

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
