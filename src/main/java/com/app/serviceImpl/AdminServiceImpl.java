package com.app.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.request.AdminLoginRequest;
import com.app.dto.response.AdminLoginResponse;
import com.app.entity.Admin;
import com.app.repository.AdminRepository;
import com.app.security.JwtUtil;
import com.app.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

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

        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Admin not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                admin.getPassword())) {

            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(admin.getEmail());

        return new AdminLoginResponse(
                token,
                "Admin Login Successful");
    }
}