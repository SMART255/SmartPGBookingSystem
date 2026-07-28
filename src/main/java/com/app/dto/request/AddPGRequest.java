package com.app.dto.request;

import com.app.enums.GenderAllowed;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddPGRequest {

    @NotBlank
    private String pgName;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String pincode;

    @NotNull
    private GenderAllowed genderAllowed;

    @Positive
    private int totalRooms;

    @Positive
    private int availableRooms;

    @Positive
    private double rentPerMonth;

    private String description;

    @NotNull
    private Long ownerId;

}