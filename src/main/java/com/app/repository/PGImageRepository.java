package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.PG;
import com.app.entity.PGImage;

public interface PGImageRepository extends JpaRepository<PGImage, Long> {

    List<PGImage> findByPg(PG pg);

}