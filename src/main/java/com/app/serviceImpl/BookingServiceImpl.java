package com.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.request.AddBookingRequest;
import com.app.dto.response.BookingResponse;
import com.app.entity.Booking;
import com.app.entity.PG;
import com.app.entity.Room;
import com.app.entity.User;
import com.app.enums.BookingStatus;
import com.app.repository.BookingRepository;
import com.app.repository.PGRepository;
import com.app.repository.RoomRepository;
import com.app.repository.UserRepository;
import com.app.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PGRepository pgRepository;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              PGRepository pgRepository,
                              RoomRepository roomRepository) {

        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.pgRepository = pgRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public BookingResponse addBooking(AddBookingRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        PG pg = pgRepository.findById(request.getPgId())
                .orElseThrow(() -> new RuntimeException("PG not found"));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setPg(pg);
        booking.setRoom(room);

        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setNumberOfBeds(request.getNumberOfBeds());

        booking.setTotalAmount(
                request.getNumberOfBeds() * room.getRent());

        booking.setStatus(BookingStatus.PENDING);

        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);

        return mapToResponse(savedBooking);
    }

    @Override
    public BookingResponse getBookingById(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return mapToResponse(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings() {

        return bookingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<BookingResponse> getBookingsByUser(Long userId) {

        return bookingRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<BookingResponse> getBookingsByPG(Long pgId) {

        return bookingRepository.findByPgId(pgId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BookingResponse updateBooking(Long id,
                                         AddBookingRequest request) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        PG pg = pgRepository.findById(request.getPgId())
                .orElseThrow(() -> new RuntimeException("PG not found"));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        booking.setUser(user);
        booking.setPg(pg);
        booking.setRoom(room);

        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setNumberOfBeds(request.getNumberOfBeds());

        booking.setTotalAmount(
                request.getNumberOfBeds() * room.getRent());

        booking.setUpdatedAt(LocalDateTime.now());

        Booking updatedBooking = bookingRepository.save(booking);

        return mapToResponse(updatedBooking);
    }

    @Override
    public void deleteBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        bookingRepository.delete(booking);
    }

    private BookingResponse mapToResponse(Booking booking) {

        BookingResponse response = new BookingResponse();

        response.setId(booking.getId());
        response.setUserName(
                booking.getUser().getFirstName() + " " +
                booking.getUser().getLastName());

        response.setPgName(booking.getPg().getPgName());

        response.setRoomNumber(
                booking.getRoom().getRoomNumber());

        response.setCheckInDate(booking.getCheckInDate());
        response.setCheckOutDate(booking.getCheckOutDate());

        response.setNumberOfBeds(booking.getNumberOfBeds());

        response.setTotalAmount(booking.getTotalAmount());

        response.setStatus(booking.getStatus());

        return response;
    }
}