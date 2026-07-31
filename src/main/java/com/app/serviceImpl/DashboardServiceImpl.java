package com.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.response.AdminDashboardResponse;
import com.app.dto.response.OwnerDashboardResponse;
import com.app.dto.response.UserDashboardResponse;
import com.app.repository.BookingRepository;
import com.app.repository.OwnerRepository;
import com.app.repository.PGRepository;
import com.app.repository.PaymentRepository;
import com.app.repository.ReviewRepository;
import com.app.repository.RoomRepository;
import com.app.repository.UserRepository;
import com.app.repository.WishlistRepository;
import com.app.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PGRepository pgRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public AdminDashboardResponse getAdminDashboard() {

        long totalUsers = userRepository.count();
        long totalOwners = ownerRepository.count();
        long totalPGs = pgRepository.count();
        long totalRooms = roomRepository.count();
        long totalBookings = bookingRepository.count();
        long totalPayments = paymentRepository.count();
        long totalReviews = reviewRepository.count();

        return new AdminDashboardResponse(
                totalUsers,
                totalOwners,
                totalPGs,
                totalRooms,
                totalBookings,
                totalPayments,
                totalReviews
        );
    }

    @Override
    public OwnerDashboardResponse getOwnerDashboard(Long ownerId) {

        long totalPGs = pgRepository.countByOwner_Id(ownerId);

        long totalRooms = roomRepository.countByPg_Owner_Id(ownerId);

        long bookedRooms = bookingRepository.countByPg_Owner_Id(ownerId);

        long availableRooms = totalRooms - bookedRooms;

        return new OwnerDashboardResponse(
                totalPGs,
                totalRooms,
                bookedRooms,
                availableRooms
        );
    }

    @Override
    public UserDashboardResponse getUserDashboard(Long userId) {

        long totalBookings = bookingRepository.countByUser_Id(userId);

        long wishlistCount = wishlistRepository.countByUser_Id(userId);

        long reviewCount = reviewRepository.countByUser_Id(userId);

        return new UserDashboardResponse(
                totalBookings,
                wishlistCount,
                reviewCount
        );
    }
    
}