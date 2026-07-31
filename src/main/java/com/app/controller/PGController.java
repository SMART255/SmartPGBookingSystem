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

import com.app.dto.request.AddPGRequest;
import com.app.dto.response.PGResponse;
import com.app.enums.GenderAllowed;
import com.app.service.PGService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pg")
@Tag(name="PG APIs")
public class PGController {

    private final PGService pgService;

    public PGController(PGService pgService) {
        this.pgService = pgService;
    }

    // =========================
    // ADD PG
    // =========================
    @Operation(summary="Add PG")
    @PostMapping("/add")
    public ResponseEntity<PGResponse> addPG(
            @RequestBody AddPGRequest request) {

        PGResponse response = pgService.addPG(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // =========================
    // GET PG BY ID
    // =========================
    @GetMapping("/{id}")
    @Operation(summary="Get PG By Id")
    public ResponseEntity<PGResponse> getPGById(
            @PathVariable Long id) {

        return ResponseEntity.ok(pgService.getPGById(id));
    }

    // =========================
    // GET ALL PGs
    // =========================
    @GetMapping("/all")
    @Operation(summary="Get All PGs")
    public ResponseEntity<List<PGResponse>> getAllPGs() {

        return ResponseEntity.ok(pgService.getAllPGs());
    }

    // =========================
    // GET PGs BY OWNER
    // =========================
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<PGResponse>> getPGsByOwner(
            @PathVariable Long ownerId) {

        return ResponseEntity.ok(pgService.getPGsByOwner(ownerId));
    }

    // =========================
    // UPDATE PG
    // =========================
    @PutMapping("/update/{id}")
    @Operation(summary="Update PG")
    public ResponseEntity<PGResponse> updatePG(
            @PathVariable Long id,
            @RequestBody AddPGRequest request) {

        return ResponseEntity.ok(pgService.updatePG(id, request));
    }

    // =========================
    // DELETE PG
    // =========================
    @DeleteMapping("/delete/{id}")
    @Operation(summary="Delete PG")
    public ResponseEntity<String> deletePG(
            @PathVariable Long id) {

        pgService.deletePG(id);

        return ResponseEntity.ok("PG Deleted Successfully");
    }

    // =========================
    // SEARCH BY CITY
    // =========================
    @GetMapping("/search/city/{city}")
    public ResponseEntity<List<PGResponse>> searchByCity(
            @PathVariable String city) {

        return ResponseEntity.ok(pgService.searchByCity(city));
    }

    // =========================
    // SEARCH BY GENDER
    // =========================
    @GetMapping("/search/gender/{gender}")
    public ResponseEntity<List<PGResponse>> searchByGender(
            @PathVariable GenderAllowed gender) {

        return ResponseEntity.ok(pgService.searchByGender(gender));
    }

    // =========================
    // SEARCH BY RENT
    // =========================
    @GetMapping("/search/rent/{rent}")
    public ResponseEntity<List<PGResponse>> searchByRent(
            @PathVariable Double rent) {

        return ResponseEntity.ok(pgService.searchByRent(rent));
    }

    // =========================
    // SEARCH BY PG NAME
    // =========================
    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<PGResponse>> searchByName(
            @PathVariable String name) {

        return ResponseEntity.ok(pgService.searchByName(name));
    }

    // =========================
    // AVAILABLE PGs
    // =========================
    @GetMapping("/available")
    public ResponseEntity<List<PGResponse>> availablePGs() {

        return ResponseEntity.ok(pgService.availablePGs());
    }

}