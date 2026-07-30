package com.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.response.PGImageResponse;

public interface PGImageService {

    PGImageResponse uploadImage(Long pgId, MultipartFile file);

    List<PGImageResponse> getImagesByPg(Long pgId);

    void deleteImage(Long imageId);
}