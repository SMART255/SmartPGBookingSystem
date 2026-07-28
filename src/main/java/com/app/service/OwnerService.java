package com.app.service;

import java.util.List;

import com.app.dto.request.RegisterOwnerRequest;
import com.app.dto.response.OwnerResponse;

public interface OwnerService {

    OwnerResponse register(RegisterOwnerRequest request);

    OwnerResponse getOwnerById(Long id);

    List<OwnerResponse> getAllOwners();

    OwnerResponse updateOwner(Long id, RegisterOwnerRequest request);

    void deleteOwner(Long id);
}