package com.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.request.AddAmenityRequest;
import com.app.dto.response.AmenityResponse;
import com.app.entity.Amenity;
import com.app.entity.PG;
import com.app.repository.AmenityRepository;
import com.app.repository.PGRepository;
import com.app.service.AmenityService;

@Service
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;
    private final PGRepository pgRepository;

    public AmenityServiceImpl(AmenityRepository amenityRepository,
                              PGRepository pgRepository) {

        this.amenityRepository = amenityRepository;
        this.pgRepository = pgRepository;
    }

    @Override
    public AmenityResponse addAmenity(AddAmenityRequest request) {

        PG pg = pgRepository.findById(request.getPgId())
                .orElseThrow(() -> new RuntimeException("PG not found"));

        Amenity amenity = new Amenity();

        amenity.setName(request.getName());
        amenity.setDescription(request.getDescription());
        amenity.setAvailable(request.getAvailable());

        amenity.setPg(pg);

        amenity.setCreatedAt(LocalDateTime.now());
        amenity.setUpdatedAt(LocalDateTime.now());

        Amenity savedAmenity = amenityRepository.save(amenity);

        return mapToResponse(savedAmenity);
    }

    @Override
    public AmenityResponse getAmenityById(Long id) {

        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        return mapToResponse(amenity);
    }

    @Override
    public List<AmenityResponse> getAllAmenities() {

        return amenityRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<AmenityResponse> getAmenitiesByPG(Long pgId) {

        return amenityRepository.findByPg_Id(pgId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AmenityResponse updateAmenity(Long id,
                                         AddAmenityRequest request) {

        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        PG pg = pgRepository.findById(request.getPgId())
                .orElseThrow(() -> new RuntimeException("PG not found"));

        amenity.setName(request.getName());
        amenity.setDescription(request.getDescription());
        amenity.setAvailable(request.getAvailable());

        amenity.setPg(pg);

        amenity.setUpdatedAt(LocalDateTime.now());

        Amenity updatedAmenity = amenityRepository.save(amenity);

        return mapToResponse(updatedAmenity);
    }

    @Override
    public void deleteAmenity(Long id) {

        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        amenityRepository.delete(amenity);
    }

    private AmenityResponse mapToResponse(Amenity amenity) {

        AmenityResponse response = new AmenityResponse();

        response.setId(amenity.getId());

        response.setPgId(amenity.getPg().getId());

        response.setPgName(amenity.getPg().getPgName());

        response.setName(amenity.getName());

        response.setDescription(amenity.getDescription());

        response.setAvailable(amenity.isAvailable());

        return response;
    }
}