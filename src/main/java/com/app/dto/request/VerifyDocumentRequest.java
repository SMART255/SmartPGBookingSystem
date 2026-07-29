package com.app.dto.request;

import com.app.enums.VerificationStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VerifyDocumentRequest {

    @NotNull(message = "Verification Status is required")
    private VerificationStatus verificationStatus;

    private String remarks;

}