package com.app.dto.response;

import com.app.enums.Gender;
import com.app.enums.Role;
import com.app.enums.Status;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Gender gender;

    private String address;

    private Role role;

    private Status status;

}