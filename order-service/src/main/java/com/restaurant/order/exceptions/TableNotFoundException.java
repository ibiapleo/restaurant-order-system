package com.restaurant.order.exceptions;

import java.util.UUID;

public class TableNotFoundException extends RuntimeException {
  public TableNotFoundException(UUID id) {
    super("Table not found with id: " + id);
  }
}
