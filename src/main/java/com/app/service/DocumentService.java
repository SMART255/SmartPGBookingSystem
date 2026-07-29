package com.app.service;

import java.util.List;

import com.app.dto.request.UploadDocumentRequest;
import com.app.dto.request.VerifyDocumentRequest;
import com.app.dto.response.DocumentResponse;

public interface DocumentService {

    // Upload Document
    DocumentResponse uploadDocument(UploadDocumentRequest request);

    // Get Document By Id
    DocumentResponse getDocumentById(Long id);

    // Get All Documents
    List<DocumentResponse> getAllDocuments();

    // Get Documents By Owner
    List<DocumentResponse> getDocumentsByOwner(Long ownerId);

    // Verify / Reject Document
    DocumentResponse verifyDocument(Long id, VerifyDocumentRequest request);

    // Delete Document
    void deleteDocument(Long id);

}