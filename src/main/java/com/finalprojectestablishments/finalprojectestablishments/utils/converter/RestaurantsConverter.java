package com.finalprojectestablishments.finalprojectestablishments.utils.converter;

import com.finalprojectestablishments.finalprojectestablishments.dto.BookingDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.ReviewDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Booking;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantsConverter {
    private ReviewConverter reviewConverter;
    public RestaurantDto restaurantToRestaurantDto(Restaurant restaurant) {
        RestaurantDto dto = new RestaurantDto();
        dto.setId(restaurant.getId());
        dto.setRestaurantName(restaurant.getRestaurantName());
        dto.setType(restaurant.getType());
        dto.setAddress(restaurant.getAddress());
        dto.setSchedule(restaurant.getSchedule());
        dto.setContacts(restaurant.getContacts());
        dto.setAverageCheck(restaurant.getAverageCheck());
//        List<ReviewDto> reviewDtoList = restaurant.getReviews().stream()
//                .map(review -> reviewConverter.reviewToReviewDto(review))
//                .collect(Collectors.toList());
//
//        dto.setReviews(reviewDtoList);
        return dto;
    }

    public List<RestaurantDto> resaturantListToRestaurantDtoList(List<Restaurant> restaurantList) {
        return restaurantList.stream().map(this::restaurantToRestaurantDto).collect(Collectors.toList());
    }
}
