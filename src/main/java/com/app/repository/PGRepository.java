package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.PG;

public interface PGRepository extends JpaRepository<PG, Long> {

    List<PG> findByOwnerId(Long ownerId);

}