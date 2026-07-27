package com.app.dto.request;

import com.app.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotBlank(message="First Name is required")
    private String firstName;

    @NotBlank(message="Last Name is required")
    private String lastName;

    @Email(message="Invalid Email")
    private String email;

    @NotBlank(message="Password is required")
    private String password;

    @NotBlank(message="Phone is required")
    private String phone;

    @NotNull(message="Gender is required")
    private Gender gender;

    private String address;

}