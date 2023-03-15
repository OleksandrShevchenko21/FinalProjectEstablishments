package com.finalprojectestablishments.finalprojectestablishments.utils.converter;


import com.finalprojectestablishments.finalprojectestablishments.dto.ReviewDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {

    public ReviewDto reviewToReviewDto(Review review) {

        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
//        dto.setUserId(review.getUser().getId());
//        dto.setRestaurantId(review.getRestaurant().getId());
//        dto.setRestaurantId(47);
        dto.setComment(review.getComment());
        dto.setRating(review.getRating());
        dto.setAverageCheck(review.getAverageCheck());

        return dto;
    }

    public List<ReviewDto> reviewListToReviewDtoList(List<Review> reviews) {
        return reviews.stream().map(this::reviewToReviewDto).collect(Collectors.toList());
    }
}