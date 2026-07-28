package com.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rooms")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int availableBeds;

    @Column(nullable = false)
    private double rent;

    @Column(nullable = false)
    private boolean attachedBathroom;

    @Column(nullable = false)
    private boolean acAvailable;

    @ManyToOne
    @JoinColumn(name = "pg_id")
    private PG pg;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}