package com.app.dto.response;

import lombok.Data;

@Data
public class AmenityResponse {

    private Long id;

    private Long pgId;

    private String pgName;

    private String name;

    private String description;

    private boolean available;

}