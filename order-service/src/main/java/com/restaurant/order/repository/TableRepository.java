package com.restaurant.order.repository;

import com.restaurant.order.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TableRepository  extends JpaRepository<Table, UUID> {
}
