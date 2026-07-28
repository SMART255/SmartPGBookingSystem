package com.app.dto.request;

import com.app.enums.PaymentMethod;

import lombok.Data;

@Data
public class AddPaymentRequest {

    private Long bookingId;

    private double amount;

    private PaymentMethod paymentMethod;
}