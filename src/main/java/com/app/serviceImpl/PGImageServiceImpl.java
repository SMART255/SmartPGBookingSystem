package com.app.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.response.PGImageResponse;
import com.app.entity.PG;
import com.app.entity.PGImage;
import com.app.repository.PGImageRepository;
import com.app.repository.PGRepository;
import com.app.service.PGImageService;

@Service
public class PGImageServiceImpl implements PGImageService {

    private final PGRepository pgRepository;
    private final PGImageRepository pgImageRepository;

    private final String UPLOAD_DIR = "uploads/";

    public PGImageServiceImpl(PGRepository pgRepository,
                              PGImageRepository pgImageRepository) {

        this.pgRepository = pgRepository;
        this.pgImageRepository = pgImageRepository;
    }

    @Override
    public PGImageResponse uploadImage(Long pgId, MultipartFile file) {

        PG pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG not found"));

        try {

            Files.createDirectories(Paths.get(UPLOAD_DIR));

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path path = Paths.get(UPLOAD_DIR, fileName);

            Files.copy(file.getInputStream(),
                    path,
                    StandardCopyOption.REPLACE_EXISTING);

            PGImage image = new PGImage();

            image.setImageUrl(fileName);
            image.setPg(pg);
            image.setCreatedAt(LocalDateTime.now());

            PGImage saved = pgImageRepository.save(image);

            return new PGImageResponse(
                    saved.getId(),
                    saved.getImageUrl());

        } catch (IOException e) {
            throw new RuntimeException("Image Upload Failed");
        }

    }

    @Override
    public List<PGImageResponse> getImagesByPg(Long pgId) {

        PG pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new RuntimeException("PG not found"));

        return pgImageRepository.findByPg(pg)
                .stream()
                .map(img -> new PGImageResponse(
                        img.getId(),
                        img.getImageUrl()))
                .toList();
    }

    @Override
    public void deleteImage(Long imageId) {

        PGImage image = pgImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        try {
            Files.deleteIfExists(Paths.get(UPLOAD_DIR, image.getImageUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pgImageRepository.delete(image);
    }
}