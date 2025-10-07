package com.restaurant.order.order_service.model.dtos.orders;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderRequestDTO {

    private UUID customerId;
    private UUID tableId;
    private List<OrderItemRequestDTO> items;

}
