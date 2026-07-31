package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.request.RegisterUserRequest;
import com.app.dto.response.UserResponse;
import com.app.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "User APIs", description = "Operations related to Users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register User
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {

        System.out.println("Controller reached");

        UserResponse response = userService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    // Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Get All Users
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Update User
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody RegisterUserRequest request) {

        return ResponseEntity.ok(
                userService.updateUser(id, request));
    }

    // Delete User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok("User Deleted Successfully");
    }
    
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(
            @RequestParam String email,
            @RequestParam String otp) {

        return ResponseEntity.ok(userService.verifyOtp(email, otp));
    }
    
    @PostMapping("/resend-otp")
    public ResponseEntity<String> resendOtp(
            @RequestParam String email) {

        return ResponseEntity.ok(userService.resendOtp(email));
    }
}