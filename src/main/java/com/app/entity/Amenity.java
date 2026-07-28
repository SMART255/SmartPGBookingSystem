package com.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

}