package com.app.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddWishlistRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "PG ID is required")
    private Long pgId;

}