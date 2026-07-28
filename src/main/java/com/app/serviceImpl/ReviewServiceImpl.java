package com.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.request.AddReviewRequest;
import com.app.dto.response.ReviewResponse;
import com.app.entity.PG;
import com.app.entity.Review;
import com.app.entity.User;
import com.app.repository.PGRepository;
import com.app.repository.ReviewRepository;
import com.app.repository.UserRepository;
import com.app.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PGRepository pgRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             UserRepository userRepository,
                             PGRepository pgRepository) {

        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.pgRepository = pgRepository;
    }

    @Override
    public ReviewResponse addReview(AddReviewRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        PG pg = pgRepository.findById(request.getPgId())
                .orElseThrow(() -> new RuntimeException("PG not found"));

        Review review = new Review();

        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setUser(user);
        review.setPg(pg);
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());

        Review savedReview = reviewRepository.save(review);

        return mapToResponse(savedReview);
    }

    @Override
    public ReviewResponse getReviewById(Long id) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        return mapToResponse(review);
    }

    @Override
    public List<ReviewResponse> getAllReviews() {

        return reviewRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ReviewResponse> getReviewsByPG(Long pgId) {

        return reviewRepository.findByPg_Id(pgId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ReviewResponse> getReviewsByUser(Long userId) {

        return reviewRepository.findByUser_Id(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ReviewResponse updateReview(Long id, AddReviewRequest request) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        PG pg = pgRepository.findById(request.getPgId())
                .orElseThrow(() -> new RuntimeException("PG not found"));

        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setUser(user);
        review.setPg(pg);
        review.setUpdatedAt(LocalDateTime.now());

        Review updatedReview = reviewRepository.save(review);

        return mapToResponse(updatedReview);
    }

    @Override
    public void deleteReview(Long id) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewRepository.delete(review);
    }

    private ReviewResponse mapToResponse(Review review) {

        ReviewResponse response = new ReviewResponse();

        response.setId(review.getId());
        response.setRating(review.getRating());
        response.setComment(review.getComment());

        response.setUserId(review.getUser().getId());
        response.setUserName(
                review.getUser().getFirstName() + " " +
                review.getUser().getLastName());

        response.setPgId(review.getPg().getId());
        response.setPgName(review.getPg().getPgName());

        response.setCreatedAt(review.getCreatedAt());

        return response;
    }
}