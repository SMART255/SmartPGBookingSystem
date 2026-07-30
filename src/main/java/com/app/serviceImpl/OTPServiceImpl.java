package com.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.app.entity.OTP;
import com.app.repository.OTPRepository;
import com.app.service.EmailService;
import com.app.service.OTPService;

@Service
public class OTPServiceImpl implements OTPService {

    private final OTPRepository otpRepository;
    private final EmailService emailService;

    public OTPServiceImpl(OTPRepository otpRepository,
                          EmailService emailService) {
        this.otpRepository = otpRepository;
        this.emailService = emailService;
    }

    @Override
    public void sendOtp(String email) {

        // Delete previous OTP if exists
        otpRepository.deleteByEmail(email);

        // Generate 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));

        OTP otpEntity = new OTP();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpEntity.setVerified(false);

        otpRepository.save(otpEntity);

        String subject = "Smart PG Booking - Email Verification OTP";

        String message =
                "Your OTP is: " + otp +
                "\n\nThis OTP is valid for 5 minutes." +
                "\nDo not share this OTP with anyone.";

        emailService.sendEmail(email, subject, message);
    }

    @Override
    public String verifyOtp(String email, String otp) {

        Optional<OTP> optionalOtp =
                otpRepository.findTopByEmailOrderByIdDesc(email);

        if (optionalOtp.isEmpty()) {
            return "OTP not found";
        }

        OTP otpEntity = optionalOtp.get();

        if (otpEntity.isVerified()) {
            return "OTP already verified";
        }

        if (LocalDateTime.now().isAfter(otpEntity.getExpiryTime())) {
            return "OTP expired";
        }

        if (!otpEntity.getOtp().equals(otp)) {
            return "Invalid OTP";
        }

        otpEntity.setVerified(true);
        otpRepository.save(otpEntity);

        return "OTP Verified Successfully";
    }
}