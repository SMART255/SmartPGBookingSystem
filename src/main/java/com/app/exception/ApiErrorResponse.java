package com.app.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;
}