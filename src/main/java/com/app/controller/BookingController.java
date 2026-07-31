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

import com.app.dto.request.AddBookingRequest;
import com.app.dto.response.BookingResponse;
import com.app.service.BookingService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/booking")
@Tag(name="Booking APIs")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public ResponseEntity<BookingResponse> addBooking(
            @RequestBody AddBookingRequest request) {

        BookingResponse response = bookingService.addBooking(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {

        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                bookingService.getBookingsByUser(userId));
    }

    @GetMapping("/pg/{pgId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByPG(
            @PathVariable Long pgId) {

        return ResponseEntity.ok(
                bookingService.getBookingsByPG(pgId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookingResponse> updateBooking(
            @PathVariable Long id,
            @RequestBody AddBookingRequest request) {

        return ResponseEntity.ok(
                bookingService.updateBooking(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBooking(
            @PathVariable Long id) {

        bookingService.deleteBooking(id);

        return ResponseEntity.ok("Booking Deleted Successfully");
    }

}