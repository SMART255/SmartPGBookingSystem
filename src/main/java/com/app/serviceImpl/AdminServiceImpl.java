package com.app.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.request.AdminLoginRequest;
import com.app.dto.response.AdminLoginResponse;
import com.app.entity.Admin;
import com.app.repository.AdminRepository;
import com.app.security.JwtUtil;
import com.app.service.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdminServiceImpl implements AdminService {
	
	  private static final Logger logger =
	            LoggerFactory.getLogger(AdminServiceImpl.class);

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AdminServiceImpl(AdminRepository adminRepository,
                            PasswordEncoder passwordEncoder,
                            JwtUtil jwtUtil) {

        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AdminLoginResponse login(AdminLoginRequest request) {

    	logger.info("Email received: {}", request.getEmail());
    	
        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        System.out.println("Entered Password: " + request.getPassword());
        System.out.println("Stored Password: " + admin.getPassword());

        boolean isMatch = passwordEncoder.matches(
                request.getPassword(),
                admin.getPassword());

        logger.info("Password Match: {}", isMatch);

        if (!isMatch) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(admin.getEmail());

        return new AdminLoginResponse(token, "Admin Login Successful");
    }
}