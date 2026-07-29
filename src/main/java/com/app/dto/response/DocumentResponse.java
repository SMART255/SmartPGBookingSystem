package com.app.dto.response;

import java.time.LocalDateTime;

import com.app.enums.DocumentType;
import com.app.enums.VerificationStatus;

import lombok.Data;

@Data
public class DocumentResponse {

    private Long id;

    private Long ownerId;

    private String ownerName;

    private DocumentType documentType;

    private String documentUrl;

    private VerificationStatus verificationStatus;

    private String remarks;

    private LocalDateTime createdAt;

}