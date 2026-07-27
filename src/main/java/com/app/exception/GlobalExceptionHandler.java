package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.dto.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handleResourceAlreadyExists(
            ResourceAlreadyExistsException ex){

        ApiResponse response =
                new ApiResponse(false, ex.getMessage());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }

}