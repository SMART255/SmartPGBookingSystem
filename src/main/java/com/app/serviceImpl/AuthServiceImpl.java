package com.app.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.request.LoginRequest;
import com.app.dto.response.LoginResponse;
import com.app.entity.User;
import com.app.repository.UserRepository;
import com.app.security.JwtUtil;
import com.app.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {

    			this.userRepository = userRepository;
    			this.passwordEncoder = passwordEncoder;
    			this.jwtUtil = jwtUtil;
}

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email or Password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Email or Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(
                token,
                "Login Successful"
        ); 
       }
}