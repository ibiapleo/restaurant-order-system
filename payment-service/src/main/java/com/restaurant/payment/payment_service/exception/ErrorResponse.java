package com.restaurant.payment.payment_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private Integer statusCode;
    private LocalDateTime timestamp;
    private String message;

}