package com.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddAmenityRequest {

    @NotNull(message = "PG ID is required")
    private Long pgId;

    @NotBlank(message = "Amenity name is required")
    private String name;

    private String description;

    @NotNull(message = "Availability is required")
    private Boolean available;

}