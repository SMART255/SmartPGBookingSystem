package com.app.dto.response;

import com.app.enums.GenderAllowed;

import lombok.Data;

@Data
public class PGResponse {

    private Long id;

    private String pgName;

    private String ownerName;

    private String address;

    private String city;

    private String state;

    private String pincode;

    private GenderAllowed genderAllowed;

    private int totalRooms;

    private int availableRooms;

    private double rentPerMonth;

    private String description;

}