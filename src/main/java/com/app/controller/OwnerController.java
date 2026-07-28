package com.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.RegisterOwnerRequest;
import com.app.dto.response.OwnerResponse;
import com.app.service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/register")
    public ResponseEntity<OwnerResponse> register(
            @RequestBody RegisterOwnerRequest request) {

        System.out.println("========== OWNER REGISTER API HIT ==========");

        OwnerResponse response = ownerService.register(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("Welcome Owner");
    }
}