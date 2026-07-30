package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.service.OTPService;

@RestController
@RequestMapping("/otp")
public class OTPController {

    private final OTPService otpService;

    public OTPController(OTPService otpService) {
        this.otpService = otpService;
    }

    // Send OTP
    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {

        otpService.sendOtp(email);

        return ResponseEntity.ok("OTP sent successfully");
    }

    // Verify OTP
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(
            @RequestParam String email,
            @RequestParam String otp) {

        String result = otpService.verifyOtp(email, otp);

        return ResponseEntity.ok(result);
    }
}