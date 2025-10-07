package com.restaurant.order.order_service.repository;

import com.restaurant.order.order_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
