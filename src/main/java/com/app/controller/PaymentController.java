package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.AddPaymentRequest;
import com.app.dto.response.PaymentResponse;
import com.app.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Add Payment
    @PostMapping("/add")
    public ResponseEntity<PaymentResponse> addPayment(
            @RequestBody AddPaymentRequest request) {

        PaymentResponse response = paymentService.addPayment(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Payment By Id
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    // Get All Payments
    @GetMapping("/all")
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {

        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // Get Payments By Booking
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByBooking(
            @PathVariable Long bookingId) {

        return ResponseEntity.ok(paymentService.getPaymentsByBooking(bookingId));
    }

    // Update Payment
    @PutMapping("/update/{id}")
    public ResponseEntity<PaymentResponse> updatePayment(
            @PathVariable Long id,
            @RequestBody AddPaymentRequest request) {

        return ResponseEntity.ok(paymentService.updatePayment(id, request));
    }

    // Delete Payment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePayment(
            @PathVariable Long id) {

        paymentService.deletePayment(id);

        return ResponseEntity.ok("Payment Deleted Successfully");
    }
}