package com.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AmenityRequest {


    @NotBlank(message="Amenity name required")
    private String name;


    private String description;

}