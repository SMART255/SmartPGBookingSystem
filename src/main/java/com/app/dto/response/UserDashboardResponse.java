package com.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDashboardResponse {

    private long totalBookings;

    private long wishlistCount;

    private long reviewCount;
}