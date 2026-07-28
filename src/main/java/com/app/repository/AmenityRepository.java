package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.Amenity;

public interface AmenityRepository 
        extends JpaRepository<Amenity, Long>{

}