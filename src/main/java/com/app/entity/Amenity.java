package com.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "amenities")
@Data
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "pg_id", nullable = false)
    private PG pg;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}