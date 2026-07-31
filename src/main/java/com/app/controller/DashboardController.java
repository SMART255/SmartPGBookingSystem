package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.response.AdminDashboardResponse;
import com.app.dto.response.OwnerDashboardResponse;
import com.app.dto.response.UserDashboardResponse;
import com.app.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // ==========================
    // Admin Dashboard
    // ==========================
    @GetMapping("/admin")
    public ResponseEntity<AdminDashboardResponse> getAdminDashboard() {

        return ResponseEntity.ok(
                dashboardService.getAdminDashboard()
        );
    }

    // ==========================
    // Owner Dashboard
    // ==========================
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<OwnerDashboardResponse> getOwnerDashboard(
            @PathVariable Long ownerId) {

        return ResponseEntity.ok(
                dashboardService.getOwnerDashboard(ownerId)
        );
    }

    // ==========================
    // User Dashboard
    // ==========================
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDashboardResponse> getUserDashboard(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                dashboardService.getUserDashboard(userId)
        );
    }

}