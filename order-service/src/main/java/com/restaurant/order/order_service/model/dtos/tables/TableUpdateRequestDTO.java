package com.restaurant.order.order_service.model.dtos.tables;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableUpdateRequestDTO {
    private int number;
    private int capacity;
}
