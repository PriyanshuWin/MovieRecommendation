package com.example.reviewapp.controller;

import java.util.List;

import com.example.reviewapp.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.reviewapp.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping
	public List<Review> getAllReviews(){
		return reviewService.getAllReviews();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(review);
    }
	
	@PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }

	@PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        Review review = reviewService.getReviewById(id);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        review.setReviewerName(reviewDetails.getReviewerName());
        review.setComment(reviewDetails.getComment());
        review.setRating(reviewDetails.getRating());
        Review updatedReview = reviewService.saveReview(review);
        return ResponseEntity.ok(updatedReview);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
	
}
