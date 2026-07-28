package com.app.service;

import java.util.List;

import com.app.dto.request.AmenityRequest;
import com.app.dto.response.AmenityResponse;


public interface AmenityService {


    AmenityResponse addAmenity(AmenityRequest request);


    List<AmenityResponse> getAllAmenities();


    AmenityResponse getAmenityById(Long id);


    AmenityResponse updateAmenity(
            Long id,
            AmenityRequest request);


    void deleteAmenity(Long id);

}