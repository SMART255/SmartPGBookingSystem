package com.app.serviceImpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.request.AmenityRequest;
import com.app.dto.response.AmenityResponse;
import com.app.entity.Amenity;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.AmenityRepository;
import com.app.service.AmenityService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AmenityServiceImpl 
        implements AmenityService {


    private final AmenityRepository repository;



    @Override
    public AmenityResponse addAmenity(
            AmenityRequest request) {


        Amenity amenity=new Amenity();

        amenity.setName(request.getName());
        amenity.setDescription(
                request.getDescription());


        Amenity saved=
                repository.save(amenity);


        return mapToResponse(saved);
    }



    @Override
    public List<AmenityResponse> getAllAmenities(){

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }



    @Override
    public AmenityResponse getAmenityById(Long id){

        Amenity amenity=
        repository.findById(id)
        .orElseThrow(
        ()->new ResourceNotFoundException(
        "Amenity not found"));

        return mapToResponse(amenity);

    }




    @Override
    public AmenityResponse updateAmenity(
            Long id,
            AmenityRequest request){


        Amenity amenity=
        repository.findById(id)
        .orElseThrow(
        ()->new ResourceNotFoundException(
        "Amenity not found"));


        amenity.setName(request.getName());

        amenity.setDescription(
                request.getDescription());


        return mapToResponse(
                repository.save(amenity));

    }




    @Override
    public void deleteAmenity(Long id){

        Amenity amenity=
        repository.findById(id)
        .orElseThrow(
        ()->new ResourceNotFoundException(
        "Amenity not found"));


        repository.delete(amenity);
    }





    private AmenityResponse mapToResponse(
            Amenity amenity){

        AmenityResponse response=
                new AmenityResponse();


        response.setId(
                amenity.getId());

        response.setName(
                amenity.getName());

        response.setDescription(
                amenity.getDescription());


        return response;
    }

}