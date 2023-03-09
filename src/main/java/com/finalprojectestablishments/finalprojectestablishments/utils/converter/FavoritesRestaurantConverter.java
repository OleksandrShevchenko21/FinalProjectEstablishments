package com.finalprojectestablishments.finalprojectestablishments.utils.converter;

import com.finalprojectestablishments.finalprojectestablishments.dto.FavoritesRestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.FavoritesRestaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FavoritesRestaurantConverter {

        public FavoritesRestaurantDto favoritesRestaurantToRestaurantDto(FavoritesRestaurant favoritesRestaurant) {
            FavoritesRestaurantDto dto = new FavoritesRestaurantDto();
            dto.setId(favoritesRestaurant.getId());
            dto.setRestaurantName(favoritesRestaurant.getRestaurantName());
            dto.setUserId(favoritesRestaurant.getUser().getId());
            dto.setRestaurantId(favoritesRestaurant.getRestaurant().getId());

            return dto;
        }

        public List<FavoritesRestaurantDto> favoritesResaturantListToRestaurantDtoList(List<FavoritesRestaurant> restaurantList) {
            return restaurantList.stream().map(this::favoritesRestaurantToRestaurantDto).collect(Collectors.toList());
        }
    }

