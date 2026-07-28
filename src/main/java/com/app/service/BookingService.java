package com.app.service;

import java.util.List;

import com.app.dto.request.AddBookingRequest;
import com.app.dto.response.BookingResponse;

public interface BookingService {

    BookingResponse addBooking(AddBookingRequest request);

    BookingResponse getBookingById(Long id);

    List<BookingResponse> getAllBookings();

    List<BookingResponse> getBookingsByUser(Long userId);

    List<BookingResponse> getBookingsByPG(Long pgId);

    BookingResponse updateBooking(Long id, AddBookingRequest request);

    void deleteBooking(Long id);

}