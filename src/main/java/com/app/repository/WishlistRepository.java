package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findByUser_Id(Long userId);

    Optional<Wishlist> findByUser_IdAndPg_Id(Long userId, Long pgId);

}