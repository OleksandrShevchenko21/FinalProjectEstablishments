package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.ReviewDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import com.finalprojectestablishments.finalprojectestablishments.services.ReviewService;
import com.finalprojectestablishments.finalprojectestablishments.services.UserService;
import com.finalprojectestablishments.finalprojectestablishments.utils.ReviewConvertor;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;
    private UserService userService;
    private RestaurantService restaurantService;
    private ReviewConvertor reviewConvertor;

    @GetMapping("")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<Review> reviews = reviewService.findAll();
        List<ReviewDto> reviewDto = reviewConvertor.reviewListToReviewDtoList(reviews);
        return new ResponseEntity<>(reviewDto, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getOneReview(@PathVariable int id) {
        Review review = reviewService.findById(id);
        ReviewDto reviewDto = reviewConvertor.reviewToReviewDto(review);
        return new ResponseEntity<>(reviewDto, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/{userId}/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReview(@PathVariable int userId,
                           @PathVariable int restaurantId,
                           @RequestBody ReviewDto reviewDto) {


        Review review = new Review();
        User user = userService.findById(userId);
        Restaurant restaurant = restaurantService.findById(restaurantId);
        review.setUser(user);
        review.setRestaurant(restaurant);
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setAverageCheck(reviewDto.getAverageCheck());
        reviewService.save(review);
    }

    @PatchMapping("/{id}")
    public void updateReview(@PathVariable int id, @RequestBody ReviewDto reviewDto) {
        Review review = reviewService.findById(id);
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        review.setAverageCheck(reviewDto.getAverageCheck());
        reviewService.update(id,review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable int id) {

        reviewService.delete(id);
    }

}
