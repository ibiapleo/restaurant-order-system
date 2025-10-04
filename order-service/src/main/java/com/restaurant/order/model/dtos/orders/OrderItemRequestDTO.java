package com.restaurant.order.model.dtos.orders;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderItemRequestDTO {
    private UUID menuItemId;
    private int quantity;
}
