package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.response.PGImageResponse;
import com.app.service.PGImageService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/image")
@Tag(name="Image Upload APIs")
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