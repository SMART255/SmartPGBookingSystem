package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "owner_documents")
public class OwnerDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private String aadhaarNumber;

    private String aadhaarFrontImage;

    private String aadhaarBackImage;

    private String panNumber;

    private String panImage;

    private String propertyProof;

    private String propertyProofImage;

    private boolean verified;
}