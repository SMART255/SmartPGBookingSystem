package com.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerDashboardResponse {

    private long totalPGs;

    private long totalRooms;

    private long bookedRooms;

    private long availableRooms;
}