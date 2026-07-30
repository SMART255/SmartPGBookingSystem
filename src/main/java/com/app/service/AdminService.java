package com.app.service;

import com.app.dto.request.AdminLoginRequest;
import com.app.dto.response.AdminLoginResponse;

public interface AdminService {

    AdminLoginResponse login(AdminLoginRequest request);

}