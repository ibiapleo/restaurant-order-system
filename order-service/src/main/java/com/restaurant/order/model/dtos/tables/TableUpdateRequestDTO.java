package com.restaurant.order.model.dtos.tables;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableUpdateRequestDTO {
    private int number;
    private int capacity;
}
