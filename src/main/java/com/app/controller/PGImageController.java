package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.response.PGImageResponse;
import com.app.service.PGImageService;

@RestController
@RequestMapping("/image")
public class PGImageController {

    private final PGImageService imageService;

    public PGImageController(PGImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload/{pgId}")
    public ResponseEntity<PGImageResponse> uploadImage(
            @PathVariable Long pgId,
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(
                imageService.uploadImage(pgId, file));
    }
}