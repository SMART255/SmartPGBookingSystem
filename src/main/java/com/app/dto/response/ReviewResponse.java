package com.app.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewResponse {

    private Long id;

    private Long userId;

    private String userName;

    private Long pgId;

    private String pgName;

    private int rating;

    private String comment;

    private LocalDateTime createdAt;
}