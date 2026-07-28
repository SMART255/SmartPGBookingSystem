package com.app.service;

import java.util.List;

import com.app.dto.request.AddPGRequest;
import com.app.dto.response.PGResponse;

public interface PGService {

    PGResponse addPG(AddPGRequest request);

    PGResponse getPGById(Long id);

    List<PGResponse> getAllPGs();

    List<PGResponse> getPGsByOwner(Long ownerId);

    PGResponse updatePG(Long id, AddPGRequest request);

    void deletePG(Long id);
}