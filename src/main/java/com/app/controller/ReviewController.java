package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.AddReviewRequest;
import com.app.dto.response.ReviewResponse;
import com.app.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // =========================
    // Add Review
    // =========================
    @PostMapping("/add")
    public ResponseEntity<ReviewResponse> addReview(
            @Valid @RequestBody AddReviewRequest request) {

        ReviewResponse response = reviewService.addReview(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // =========================
    // Get Review By ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(
            @PathVariable Long id) {

        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    // =========================
    // Get All Reviews
    // =========================
    @GetMapping("/all")
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {

        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    // =========================
    // Get Reviews By PG
    // =========================
    @GetMapping("/pg/{pgId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByPG(
            @PathVariable Long pgId) {

        return ResponseEntity.ok(reviewService.getReviewsByPG(pgId));
    }

    // =========================
    // Get Reviews By User
    // =========================
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(reviewService.getReviewsByUser(userId));
    }

    // =========================
    // Update Review
    // =========================
    @PutMapping("/update/{id}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long id,
            @Valid @RequestBody AddReviewRequest request) {

        return ResponseEntity.ok(reviewService.updateReview(id, request));
    }

    // =========================
    // Delete Review
    // =========================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(
            @PathVariable Long id) {

        reviewService.deleteReview(id);

        return ResponseEntity.ok("Review Deleted Successfully");
    }
}