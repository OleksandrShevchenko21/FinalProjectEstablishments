package com.finalprojectestablishments.finalprojectestablishments.utils.converter;

import com.finalprojectestablishments.finalprojectestablishments.dao.RestaurantDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RestaurantsConverter {
private RestaurantDao restaurantDao;
//    public RestaurantsConverter(RestaurantService restaurantService) {
//        this.restaurantService = restaurantService;
//    }
    public RestaurantDto restaurantToRestaurantDto(Restaurant restaurant) {
        RestaurantDto dto = new RestaurantDto();
        dto.setId(restaurant.getId());
        dto.setRestaurantName(restaurant.getRestaurantName());
        dto.setType(restaurant.getType());
        dto.setAddress(restaurant.getAddress());
        dto.setSchedule(restaurant.getSchedule());
        dto.setContacts(restaurant.getContacts());
        dto.setAverageCheck(restaurant.getAverageCheck());
        dto.setDateOfPublish(restaurant.getDateOfPublish());
        Double avgRating = restaurantDao.getAvgRatingByRestaurantId(restaurant.getId());
        dto.setAverageRating(avgRating);
//        String userName = restaurant.getUsers().stream().findFirst().map(user -> user.getUserName()).orElse(null);
//        dto.setUserName(userName);

        return dto;
    }

    public List<RestaurantDto> resaturantListToRestaurantDtoList(List<Restaurant> restaurantList) {
        return restaurantList.stream().map(this::restaurantToRestaurantDto).collect(Collectors.toList());
    }
}
