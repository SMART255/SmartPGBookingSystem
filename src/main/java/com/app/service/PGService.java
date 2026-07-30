package com.app.service;

import java.util.List;

import com.app.dto.request.AddPGRequest;
import com.app.dto.response.PGResponse;
import com.app.enums.GenderAllowed;

public interface PGService {

    PGResponse addPG(AddPGRequest request);

    PGResponse getPGById(Long id);

    List<PGResponse> getAllPGs();

    List<PGResponse> getPGsByOwner(Long ownerId);

    PGResponse updatePG(Long id, AddPGRequest request);

    void deletePG(Long id);
    
    List<PGResponse> searchByCity(String city);

    List<PGResponse> searchByGender(GenderAllowed gender);

    List<PGResponse> searchByRent(Double rent);

    List<PGResponse> searchByName(String name);

    List<PGResponse> availablePGs();
}