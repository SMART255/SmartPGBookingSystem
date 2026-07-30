package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.OTP;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long> {

    Optional<OTP> findTopByEmailOrderByIdDesc(String email);

    void deleteByEmail(String email);
}