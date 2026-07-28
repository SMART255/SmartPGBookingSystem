package com.app.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AddBookingRequest {

    private Long userId;

    private Long pgId;

    private Long roomId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numberOfBeds;
}