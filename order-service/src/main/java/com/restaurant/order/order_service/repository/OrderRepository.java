package com.restaurant.order.order_service.repository;

import com.restaurant.order.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE orders SET status = :status WHERE id = :orderId", nativeQuery = true)
    void updateOrder(@Param("orderId") UUID orderId, @Param("status") String status);
}