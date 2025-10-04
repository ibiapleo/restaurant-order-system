package com.restaurant.order.order_service.repository;

import com.restaurant.order.order_service.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TableRepository  extends JpaRepository<Table, UUID> {
}
