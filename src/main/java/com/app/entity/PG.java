package com.app.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.app.enums.GenderAllowed;
import com.app.enums.Status;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pgs")
@Data
public class PG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String pgName;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false)
    private String address;

    private String city;

    private String state;

    private String pincode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderAllowed genderAllowed;

    private int totalRooms;

    private int availableRooms;

    private double rentPerMonth;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Many PGs belong to one Owner
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    // One PG can have multiple images
    @OneToMany(mappedBy = "pg", cascade = CascadeType.ALL)
    private List<PGImage> images;

    // One PG can have multiple rooms
    @OneToMany(mappedBy = "pg", cascade = CascadeType.ALL)
    private List<Room> rooms;

}