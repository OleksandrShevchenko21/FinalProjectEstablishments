package com.finalprojectestablishments.finalprojectestablishments.utils.converter;

import com.finalprojectestablishments.finalprojectestablishments.dto.BookingDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Booking;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantsConverter {
        public RestaurantDto restaurantToRestaurantDto(Restaurant restaurant) {
            RestaurantDto dto = new RestaurantDto();
            dto.setId(restaurant.getId());
            dto.setRestaurantName(restaurant.getRestaurantName());

            return dto;
        }

        public List<RestaurantDto> resaturantListToRestaurantDtoList(List<Restaurant> restaurantList) {
            return restaurantList.stream().map(this::restaurantToRestaurantDto).collect(Collectors.toList());
        }
    }
