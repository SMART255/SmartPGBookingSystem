package com.app.dto.response;

import java.time.LocalDate;

import com.app.enums.BookingStatus;

import lombok.Data;

@Data
public class BookingResponse {

    private Long id;

    private String userName;

    private String pgName;

    private String roomNumber;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numberOfBeds;

    private double totalAmount;

    private BookingStatus status;
}