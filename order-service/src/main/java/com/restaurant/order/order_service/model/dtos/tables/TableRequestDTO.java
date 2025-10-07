package com.restaurant.order.order_service.model.dtos.tables;

import com.restaurant.order.order_service.model.enums.TableStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableRequestDTO {
    private int number;
    private int capacity;
    private TableStatus status;
}
