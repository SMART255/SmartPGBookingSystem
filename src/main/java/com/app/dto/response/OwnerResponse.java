package com.app.dto.response;

import com.app.enums.Status;

import lombok.Data;

@Data
public class OwnerResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String gender;

    private String address;

    private boolean verified;

    private Status status;
}