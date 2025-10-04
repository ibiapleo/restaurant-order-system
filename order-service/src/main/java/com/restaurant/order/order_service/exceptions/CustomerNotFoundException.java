package com.restaurant.order.order_service.exceptions;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(UUID id) {
        super("Customer not found with id: " + id);
    }
}