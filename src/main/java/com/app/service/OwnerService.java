package com.app.service;

import com.app.dto.request.RegisterOwnerRequest;
import com.app.dto.response.OwnerResponse;

public interface OwnerService {

    OwnerResponse register(RegisterOwnerRequest request);
}