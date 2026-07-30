package com.app.service;

public interface OTPService {

    void sendOtp(String email);

    String verifyOtp(String email, String otp);

}