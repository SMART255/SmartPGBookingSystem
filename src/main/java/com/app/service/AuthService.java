package com.app.service;

import com.app.dto.request.LoginRequest;
import com.app.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}