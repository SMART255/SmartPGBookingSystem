package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    List<Amenity> findByPg_Id(Long pgId);

}