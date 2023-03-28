package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.ReviewDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import com.finalprojectestablishments.finalprojectestablishments.services.ReviewService;
import com.finalprojectestablishments.finalprojectestablishments.services.UserService;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.ReviewConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reviews/restaurant")

public class ReviewController {

    private ReviewService reviewService;
    private UserService userService;
    private RestaurantService restaurantService;
    private ReviewConverter reviewConvertor;

    @GetMapping("")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<Review> reviews = reviewService.findAll();
        List<ReviewDto> reviewDto = reviewConvertor.reviewListToReviewDtoList(reviews);
        return new ResponseEntity<>(reviewDto, HttpStatus.valueOf(200));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<ReviewDto>> getAllReviewsByRestaurantID(@PathVariable int restaurantId) {
        List<Review> reviews = reviewService.findByRestaurantId(restaurantId);
        List<ReviewDto> reviewDto = reviewConvertor.reviewListToReviewDtoList(reviews);
        return new ResponseEntity<>(reviewDto, HttpStatus.valueOf(200));
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<ReviewDto> getOneReview(@PathVariable int id) {
        Review review = reviewService.findById(id);
        ReviewDto reviewDto = reviewConvertor.reviewToReviewDto(review);
        return new ResponseEntity<>(reviewDto, HttpStatus.valueOf(200));
    }

    @PostMapping("/admin/{restaurantId}/{userName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReview(
            @PathVariable int restaurantId,
            @PathVariable String userName,
            @RequestBody ReviewDto reviewDto) {

        Review review = new Review();
        User user = userService.findByUserName(userName);
        Restaurant restaurant = restaurantService.findById(restaurantId);
        review.setUser(user);
        review.setRestaurant(restaurant);
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setAverageCheck(reviewDto.getAverageCheck());
        reviewService.save(review);
    }

    @PatchMapping("/admin/{id}")
    public void updateReview(@PathVariable int id, @RequestBody ReviewDto reviewDto) {
        Review review = reviewService.findById(id);
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        review.setAverageCheck(reviewDto.getAverageCheck());
        reviewService.update(id, review);
    }

    @DeleteMapping("/admin/{id}")
    public void deleteReview(@PathVariable int id) {

        reviewService.delete(id);
    }
}
