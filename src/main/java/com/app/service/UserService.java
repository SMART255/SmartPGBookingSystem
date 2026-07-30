package com.app.service;

import java.util.List;

import com.app.dto.request.LoginRequest;
import com.app.dto.request.RegisterUserRequest;
import com.app.dto.response.LoginResponse;
import com.app.dto.response.UserResponse;

public interface UserService {

    UserResponse register(RegisterUserRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, RegisterUserRequest request);

    void deleteUser(Long id);

    LoginResponse login(LoginRequest request);

    String verifyOtp(String email, String otp);

    String resendOtp(String email);
}