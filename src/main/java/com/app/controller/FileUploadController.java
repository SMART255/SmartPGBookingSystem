package com.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.service.FileUploadService;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    // ===========================
    // Upload File
    // ===========================
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {

        String fileName = fileUploadService.uploadFile(file);

        return ResponseEntity.ok(fileName);
    }

    // ===========================
    // Download File
    // ===========================
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String fileName) throws IOException {

        Path path = Paths.get(uploadDir).resolve(fileName);

        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {
            throw new RuntimeException("File not found");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    // ===========================
    // Delete File
    // ===========================
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(
            @PathVariable String fileName) throws IOException {

        Path path = Paths.get(uploadDir).resolve(fileName);

        Files.deleteIfExists(path);

        return ResponseEntity.ok("File Deleted Successfully");
    }

}