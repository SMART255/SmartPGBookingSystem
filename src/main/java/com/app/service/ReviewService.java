package com.app.service;

import java.util.List;

import com.app.dto.request.AddReviewRequest;
import com.app.dto.response.ReviewResponse;

public interface ReviewService {

    ReviewResponse addReview(AddReviewRequest request);

    ReviewResponse getReviewById(Long id);

    List<ReviewResponse> getAllReviews();

    List<ReviewResponse> getReviewsByPG(Long pgId);

    List<ReviewResponse> getReviewsByUser(Long userId);

    ReviewResponse updateReview(Long id, AddReviewRequest request);

    void deleteReview(Long id);

}