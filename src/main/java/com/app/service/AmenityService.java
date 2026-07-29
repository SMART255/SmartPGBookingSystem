package com.app.service;

import java.util.List;

import com.app.dto.request.AddAmenityRequest;
import com.app.dto.response.AmenityResponse;

public interface AmenityService {

    AmenityResponse addAmenity(AddAmenityRequest request);

    AmenityResponse getAmenityById(Long id);

    List<AmenityResponse> getAllAmenities();

    List<AmenityResponse> getAmenitiesByPG(Long pgId);

    AmenityResponse updateAmenity(Long id, AddAmenityRequest request);

    void deleteAmenity(Long id);

}