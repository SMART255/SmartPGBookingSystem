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
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.request.RegisterOwnerRequest;
import com.app.dto.response.OwnerResponse;
import com.app.service.OwnerService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    @Operation(summary="Register Owner")
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
    
    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwnerById(@PathVariable Long id) {

        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<OwnerResponse>> getAllOwners() {

        return ResponseEntity.ok(ownerService.getAllOwners());
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<OwnerResponse> updateOwner(
            @PathVariable Long id,
            @RequestBody RegisterOwnerRequest request) {

        return ResponseEntity.ok(ownerService.updateOwner(id, request));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Long id) {

        ownerService.deleteOwner(id);

        return ResponseEntity.ok("Owner Deleted Successfully");
    }
    
    
}