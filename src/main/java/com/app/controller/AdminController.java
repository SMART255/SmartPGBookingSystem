package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.request.AdminLoginRequest;
import com.app.dto.response.AdminLoginResponse;
import com.app.service.AdminService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/admin")
@Tag(name="Admin APIs", description="Admin Operations")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponse> login(
            @RequestBody AdminLoginRequest request) {

        return ResponseEntity.ok(adminService.login(request));
    }
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/admin-password")
    public String password() {
        return passwordEncoder.encode("admin123");
    }
}