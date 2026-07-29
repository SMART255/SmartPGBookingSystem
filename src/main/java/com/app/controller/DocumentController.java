package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.UploadDocumentRequest;
import com.app.dto.request.VerifyDocumentRequest;
import com.app.dto.response.DocumentResponse;
import com.app.service.DocumentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // ===========================
    // Upload Document
    // ===========================
    @PostMapping("/upload")
    public ResponseEntity<DocumentResponse> uploadDocument(
            @Valid @RequestBody UploadDocumentRequest request) {

        DocumentResponse response = documentService.uploadDocument(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ===========================
    // Get Document By ID
    // ===========================
    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getDocumentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(documentService.getDocumentById(id));
    }

    // ===========================
    // Get All Documents
    // ===========================
    @GetMapping("/all")
    public ResponseEntity<List<DocumentResponse>> getAllDocuments() {

        return ResponseEntity.ok(documentService.getAllDocuments());
    }

    // ===========================
    // Get Documents By Owner
    // ===========================
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<DocumentResponse>> getDocumentsByOwner(
            @PathVariable Long ownerId) {

        return ResponseEntity.ok(documentService.getDocumentsByOwner(ownerId));
    }

    // ===========================
    // Verify / Reject Document
    // ===========================
    @PutMapping("/verify/{id}")
    public ResponseEntity<DocumentResponse> verifyDocument(
            @PathVariable Long id,
            @Valid @RequestBody VerifyDocumentRequest request) {

        return ResponseEntity.ok(documentService.verifyDocument(id, request));
    }

    // ===========================
    // Delete Document
    // ===========================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDocument(
            @PathVariable Long id) {

        documentService.deleteDocument(id);

        return ResponseEntity.ok("Document Deleted Successfully");
    }
}