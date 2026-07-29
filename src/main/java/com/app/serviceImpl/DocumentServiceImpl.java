package com.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.request.UploadDocumentRequest;
import com.app.dto.request.VerifyDocumentRequest;
import com.app.dto.response.DocumentResponse;
import com.app.entity.Document;
import com.app.entity.Owner;
import com.app.enums.VerificationStatus;
import com.app.repository.DocumentRepository;
import com.app.repository.OwnerRepository;
import com.app.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {


    private final DocumentRepository documentRepository;

    private final OwnerRepository ownerRepository;



    public DocumentServiceImpl(
            DocumentRepository documentRepository,
            OwnerRepository ownerRepository) {

        this.documentRepository = documentRepository;
        this.ownerRepository = ownerRepository;
    }



    // ===============================
    // Upload Document
    // ===============================
    @Override
    public DocumentResponse uploadDocument(UploadDocumentRequest request) {

        System.out.println("STEP 1: Owner ID = " + request.getOwnerId());


        Owner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> 
                    new RuntimeException("Owner not found"));


        System.out.println("STEP 2: Owner Found = " 
                + owner.getFirstName());


        Document document = new Document();


        document.setDocumentType(request.getDocumentType());

        document.setDocumentUrl(request.getDocumentUrl());

        document.setVerificationStatus(
                VerificationStatus.PENDING);

        document.setRemarks(
                "Waiting for Admin Verification");


        document.setOwner(owner);


        document.setCreatedAt(LocalDateTime.now());

        document.setUpdatedAt(LocalDateTime.now());


        System.out.println("STEP 3: Saving Document");


        Document savedDocument = documentRepository.save(document);


        System.out.println("STEP 4: Saved Document ID = "
                + savedDocument.getId());


        return mapToResponse(savedDocument);
    }



    // ===============================
    // Get Document By ID
    // ===============================
    @Override
    public DocumentResponse getDocumentById(Long id) {


        Document document =
                documentRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                            "Document not found")
                );


        return mapToResponse(document);
    }




    // ===============================
    // Get All Documents
    // ===============================
    @Override
    public List<DocumentResponse> getAllDocuments() {


        return documentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();

    }





    // ===============================
    // Get Documents By Owner
    // ===============================
    @Override
    public List<DocumentResponse> getDocumentsByOwner(
            Long ownerId) {


        return documentRepository
                .findByOwner_Id(ownerId)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }





    // ===============================
    // Verify / Reject Document
    // ===============================
    @Override
    public DocumentResponse verifyDocument(
            Long id,
            VerifyDocumentRequest request) {


        Document document =
                documentRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                            "Document not found")
                );



        document.setVerificationStatus(
                request.getVerificationStatus()
        );


        document.setRemarks(
                request.getRemarks()
        );


        document.setUpdatedAt(
                LocalDateTime.now()
        );



        Document updatedDocument =
                documentRepository.save(document);



        return mapToResponse(updatedDocument);

    }





    // ===============================
    // Delete Document
    // ===============================
    @Override
    public void deleteDocument(Long id) {


        Document document =
                documentRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                            "Document not found")
                );


        documentRepository.delete(document);

    }





    // ===============================
    // Entity To Response Mapping
    // ===============================
    private DocumentResponse mapToResponse(
            Document document) {


        DocumentResponse response =
                new DocumentResponse();



        response.setId(
                document.getId()
        );



        response.setOwnerId(
                document.getOwner().getId()
        );



        response.setOwnerName(
                document.getOwner().getFirstName()
                + " "
                + document.getOwner().getLastName()
        );



        response.setDocumentType(
                document.getDocumentType()
        );



        response.setDocumentUrl(
                document.getDocumentUrl()
        );



        response.setVerificationStatus(
                document.getVerificationStatus()
        );



        response.setRemarks(
                document.getRemarks()
        );



        response.setCreatedAt(
                document.getCreatedAt()
        );



        return response;
    }

}