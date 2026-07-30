package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.AddWishlistRequest;
import com.app.dto.response.WishlistResponse;
import com.app.service.WishlistService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // ==========================
    // Add to Wishlist
    // ==========================
    @PostMapping("/add")
    public ResponseEntity<WishlistResponse> addToWishlist(
            @Valid @RequestBody AddWishlistRequest request) {

        WishlistResponse response = wishlistService.addToWishlist(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ==========================
    // Get User Wishlist
    // ==========================
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WishlistResponse>> getUserWishlist(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                wishlistService.getUserWishlist(userId));
    }

    // ==========================
    // Remove from Wishlist
    // ==========================
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeFromWishlist(
            @PathVariable Long id) {

        wishlistService.removeFromWishlist(id);

        return ResponseEntity.ok("Wishlist item removed successfully.");
    }

}