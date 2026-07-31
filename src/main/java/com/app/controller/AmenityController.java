package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.request.AddAmenityRequest;
import com.app.dto.response.AmenityResponse;
import com.app.service.AmenityService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/amenity")
@Tag(name="Amenities APIs")
public class AmenityController {

    private final AmenityService amenityService;

    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    // =========================
    // Add Amenity
    // =========================
    @PostMapping("/add")
    public ResponseEntity<AmenityResponse> addAmenity(
            @Valid @RequestBody AddAmenityRequest request) {

        AmenityResponse response = amenityService.addAmenity(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // =========================
    // Get Amenity By Id
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<AmenityResponse> getAmenityById(
            @PathVariable Long id) {

        return ResponseEntity.ok(amenityService.getAmenityById(id));
    }

    // =========================
    // Get All Amenities
    // =========================
    @GetMapping("/all")
    public ResponseEntity<List<AmenityResponse>> getAllAmenities() {

        return ResponseEntity.ok(amenityService.getAllAmenities());
    }

    // =========================
    // Get Amenities By PG
    // =========================
    @GetMapping("/pg/{pgId}")
    public ResponseEntity<List<AmenityResponse>> getAmenitiesByPG(
            @PathVariable Long pgId) {

        return ResponseEntity.ok(amenityService.getAmenitiesByPG(pgId));
    }

    // =========================
    // Update Amenity
    // =========================
    @PutMapping("/update/{id}")
    public ResponseEntity<AmenityResponse> updateAmenity(
            @PathVariable Long id,
            @Valid @RequestBody AddAmenityRequest request) {

        return ResponseEntity.ok(
                amenityService.updateAmenity(id, request));
    }

    // =========================
    // Delete Amenity
    // =========================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAmenity(
            @PathVariable Long id) {

        amenityService.deleteAmenity(id);

        return ResponseEntity.ok("Amenity Deleted Successfully");
    }

}