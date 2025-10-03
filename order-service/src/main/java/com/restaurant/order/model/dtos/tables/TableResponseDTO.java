package com.restaurant.order.model.dtos.tables;

import com.restaurant.order.model.enums.TableStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TableResponseDTO {
    private UUID id;
    private int number;
    private int capacity;
    private TableStatus status;
}
