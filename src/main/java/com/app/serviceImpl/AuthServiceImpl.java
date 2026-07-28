package com.app.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.request.LoginRequest;
import com.app.dto.response.LoginResponse;
import com.app.entity.Owner;
import com.app.repository.OwnerRepository;
import com.app.security.JwtUtil;
import com.app.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(OwnerRepository ownerRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {

        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Owner owner = ownerRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email or Password"));

        if (!passwordEncoder.matches(request.getPassword(), owner.getPassword())) {
            throw new RuntimeException("Invalid Email or Password");
        }

        String token = jwtUtil.generateToken(owner.getEmail());

        return new LoginResponse(
                token,
                "Login Successful"
        );
    }
}