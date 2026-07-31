package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Owner;
import com.app.entity.PG;
import com.app.enums.GenderAllowed;
import org.springframework.data.jpa.repository.Query;

public interface PGRepository extends JpaRepository<PG, Long> {

    List<PG> findByCityIgnoreCase(String city);

    List<PG> findByGenderAllowed(GenderAllowed genderAllowed);

    List<PG> findByRentPerMonthLessThanEqual(Double rent);

    @Query("SELECT p FROM PG p WHERE UPPER(p.pgName) LIKE UPPER(:pgName) ESCAPE '\\'")
	List<PG> findByPgNameContainingIgnoreCase(String pgName);

    List<PG> findByAvailableRoomsGreaterThan(int rooms);
    
    List<PG> findByOwner_Id(Long ownerId);
  

	long countByOwner_Id(Long ownerId);

	
}