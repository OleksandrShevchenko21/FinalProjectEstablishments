package com.finalprojectestablishments.finalprojectestablishments.utils;


import com.finalprojectestablishments.finalprojectestablishments.dto.ReviewDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConvertor {

    public ReviewDto reviewToReviewDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setUserId(review.getUser().getId());
        dto.setRestaurantId(review.getRestaurant().getId());
        dto.setComment(review.getComment());
        dto.setRating(review.getRating());
        dto.setAverageCheck(review.getAverageCheck());
        return dto;
    }

    public List<ReviewDto> reviewListToReviewDtoList(List<Review> reviews) {
        return reviews.stream().map(this::reviewToReviewDto).collect(Collectors.toList());
    }
}