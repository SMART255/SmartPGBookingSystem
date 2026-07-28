package com.app.dto.response;

import java.time.LocalDateTime;

import com.app.enums.PaymentMethod;
import com.app.enums.PaymentStatus;

import lombok.Data;

@Data
public class PaymentResponse {

    private Long id;

    private Long bookingId;

    private double amount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private String transactionId;

    private LocalDateTime paymentDate;
}