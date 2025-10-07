package com.restaurant.payment.payment_service.domain.payment;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final ModelMapper mapper;

    public PaymentResponse processPayment(PaymentRequest request) {
        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .method(request.getMethod())
                .amount(request.getAmount())
                .status(PaymentStatus.APPROVED) // simulação: sempre aprovado
                .build();

        return mapper.map(repository.save(payment), PaymentResponse.class);
    }

    public PaymentResponse getPayment(UUID id) {
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + id));
        return mapper.map(payment, PaymentResponse.class);
    }

    public PaymentResponse refundPayment(UUID id) {
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + id));
        payment.setStatus(PaymentStatus.REFUNDED);
        return mapper.map(repository.save(payment), PaymentResponse.class);
    }
}