package com.restaurant.menu.menu_service.domain.menu;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MenuItemRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Min(0)
    private Double price;

    @NotNull
    private ItemCategory category;
}
