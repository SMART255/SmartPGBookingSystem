package com.app.service;

import java.util.List;

import com.app.dto.request.AddPaymentRequest;
import com.app.dto.response.PaymentResponse;

public interface PaymentService {

    PaymentResponse addPayment(AddPaymentRequest request);

    PaymentResponse getPaymentById(Long id);

    List<PaymentResponse> getAllPayments();

    List<PaymentResponse> getPaymentsByBooking(Long bookingId);

    PaymentResponse updatePayment(Long id, AddPaymentRequest request);

    void deletePayment(Long id);
}