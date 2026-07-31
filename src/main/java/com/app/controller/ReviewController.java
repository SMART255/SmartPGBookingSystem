package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.request.AddReviewRequest;
import com.app.dto.response.ReviewResponse;
import com.app.service.ReviewService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/review")
@Tag(name="Review APIs")
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