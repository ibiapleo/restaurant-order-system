package com.restaurant.order.model.dtos.tables;

import com.restaurant.order.model.enums.TableStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableRequestDTO {
    private int number;
    private int capacity;
    private TableStatus status;
}
