package com.app.service;

import com.app.dto.request.RegisterUserRequest;
import com.app.dto.response.UserResponse;

public interface UserService {

    UserResponse register(RegisterUserRequest request);

}