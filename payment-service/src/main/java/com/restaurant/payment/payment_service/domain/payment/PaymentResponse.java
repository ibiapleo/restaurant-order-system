package com.restaurant.payment.payment_service.domain.payment;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentResponse {
    private UUID id;
    private UUID orderId;
    private PaymentMethod method;
    private BigDecimal amount;
    private PaymentStatus status;
}