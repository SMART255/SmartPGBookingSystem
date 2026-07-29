package com.app.entity;
import java.util.List;

import java.time.LocalDateTime;

import com.app.enums.Status;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "owner")
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;

    private String gender;

    private String address;

    // Aadhaar, PAN, etc. (added later)
    private String document;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean verified;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<PG> pgs;
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Document> documents;
}