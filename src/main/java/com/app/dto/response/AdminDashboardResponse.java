package com.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDashboardResponse {

    private long totalUsers;

    private long totalOwners;

    private long totalPGs;

    private long totalRooms;

    private long totalBookings;

    private long totalPayments;

    private long totalReviews;
}