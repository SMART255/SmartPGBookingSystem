package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Document;
import com.app.enums.VerificationStatus;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByOwner_Id(Long ownerId);

    List<Document> findByVerificationStatus(VerificationStatus verificationStatus);

}