package com.restaurant.order.order_service.model;

import com.restaurant.order.order_service.model.enums.TableStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

@Entity
@jakarta.persistence.Table(name = "tables")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int number;
    private int capacity;
    private TableStatus status;


}
