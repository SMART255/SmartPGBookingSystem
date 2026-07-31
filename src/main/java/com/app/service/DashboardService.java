package com.app.service;

import com.app.dto.response.AdminDashboardResponse;
import com.app.dto.response.OwnerDashboardResponse;
import com.app.dto.response.UserDashboardResponse;

public interface DashboardService {

	AdminDashboardResponse getAdminDashboard();

	OwnerDashboardResponse getOwnerDashboard(Long ownerId);

	UserDashboardResponse getUserDashboard(Long userId);
	
}
