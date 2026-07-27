package com.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.RegisterUserRequest;
import com.app.dto.response.UserResponse;
import com.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Validated @RequestBody RegisterUserRequest request) {

        UserResponse response = userService.register(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
    
    @GetMapping("/profile")
    public ResponseEntity<String> profile() {

        return ResponseEntity.ok("Welcome User");
    }

}