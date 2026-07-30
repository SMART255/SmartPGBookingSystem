package com.app.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class WishlistResponse {

    private Long id;

    private Long userId;

    private String userName;

    private Long pgId;

    private String pgName;

    private LocalDateTime createdAt;

}