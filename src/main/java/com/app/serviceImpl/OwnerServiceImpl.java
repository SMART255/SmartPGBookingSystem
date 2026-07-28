package com.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.request.RegisterOwnerRequest;
import com.app.dto.response.OwnerResponse;
import com.app.entity.Owner;
import com.app.enums.Status;
import com.app.exception.ResourceAlreadyExistsException;
import com.app.repository.OwnerRepository;
import com.app.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public OwnerServiceImpl(OwnerRepository ownerRepository,
                            PasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OwnerResponse register(RegisterOwnerRequest request) {

        if (ownerRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        if (ownerRepository.existsByPhone(request.getPhone())) {
            throw new ResourceAlreadyExistsException("Phone already exists");
        }

        Owner owner = new Owner();

        owner.setFirstName(request.getFirstName());
        owner.setLastName(request.getLastName());
        owner.setEmail(request.getEmail());
        owner.setPassword(passwordEncoder.encode(request.getPassword()));
        owner.setPhone(request.getPhone());
        owner.setGender(request.getGender());
        owner.setAddress(request.getAddress());

        owner.setVerified(true);
        owner.setStatus(Status.ACTIVE);
        
        owner.setCreatedAt(LocalDateTime.now());
        owner.setUpdatedAt(LocalDateTime.now());

        Owner savedOwner = ownerRepository.save(owner);

        OwnerResponse response = new OwnerResponse();

        response.setId(savedOwner.getId());
        response.setFirstName(savedOwner.getFirstName());
        response.setLastName(savedOwner.getLastName());
        response.setEmail(savedOwner.getEmail());
        response.setPhone(savedOwner.getPhone());
        response.setGender(savedOwner.getGender());
        response.setAddress(savedOwner.getAddress());
        response.setVerified(savedOwner.isVerified());
        response.setStatus(savedOwner.getStatus());

        return response;
    }
    
    @Override
    public OwnerResponse getOwnerById(Long id) {

        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        return mapToResponse(owner);
    }
    
    @Override
    public List<OwnerResponse> getAllOwners() {

        return ownerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    
    @Override
    public OwnerResponse updateOwner(Long id,
                                     RegisterOwnerRequest request) {

        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        owner.setFirstName(request.getFirstName());
        owner.setLastName(request.getLastName());
        owner.setEmail(request.getEmail());
        owner.setPhone(request.getPhone());
        owner.setGender(request.getGender());
        owner.setAddress(request.getAddress());

        if (request.getPassword() != null &&
            !request.getPassword().isBlank()) {

            owner.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        owner.setUpdatedAt(LocalDateTime.now());

        Owner updatedOwner = ownerRepository.save(owner);

        return mapToResponse(updatedOwner);
    }
    
    @Override
    public void deleteOwner(Long id) {

        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        ownerRepository.delete(owner);
    }
    
    private OwnerResponse mapToResponse(Owner owner) {

        OwnerResponse response = new OwnerResponse();

        response.setId(owner.getId());
        response.setFirstName(owner.getFirstName());
        response.setLastName(owner.getLastName());
        response.setEmail(owner.getEmail());
        response.setPhone(owner.getPhone());
        response.setGender(owner.getGender());
        response.setAddress(owner.getAddress());
        response.setVerified(owner.isVerified());
        response.setStatus(owner.getStatus());

        return response;
    }
}