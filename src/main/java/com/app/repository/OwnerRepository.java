package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<Owner> findByEmail(String email);
}