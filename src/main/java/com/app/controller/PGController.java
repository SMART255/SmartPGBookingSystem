package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.AddPGRequest;
import com.app.dto.response.PGResponse;
import com.app.service.PGService;
@RestController
@RequestMapping("/pg")
public class PGController {

    private final PGService pgService;

    public PGController(PGService pgService) {
        this.pgService = pgService;
    }
    
    @PostMapping("/add")
    public ResponseEntity<PGResponse> addPG(@RequestBody AddPGRequest request) {

        PGResponse response = pgService.addPG(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PGResponse> getPGById(@PathVariable Long id) {

        return ResponseEntity.ok(pgService.getPGById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PGResponse>> getAllPGs() {

        return ResponseEntity.ok(pgService.getAllPGs());
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<PGResponse>> getPGsByOwner(@PathVariable Long ownerId) {

        return ResponseEntity.ok(pgService.getPGsByOwner(ownerId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PGResponse> updatePG(
            @PathVariable Long id,
            @RequestBody AddPGRequest request) {

        return ResponseEntity.ok(pgService.updatePG(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePG(@PathVariable Long id) {

        pgService.deletePG(id);

        return ResponseEntity.ok("PG Deleted Successfully");
    }
}