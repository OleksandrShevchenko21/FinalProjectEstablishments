package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.RestaurantDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.RestaurantsConverter;
import lombok.AllArgsConstructor;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {
    private RestaurantDao restaurantDao;
    private RestaurantsConverter restaurantsConverter;
    private final ModelMapper modelMapper;

//public Page<RestaurantDto> findAll(Pageable pageable) {
//    Page<Restaurant> restaurants = restaurantDao.findAll(pageable);
//    return restaurants.map(this::convertToDto);
//
//}
public List<RestaurantDto> findAll() {
    List<Restaurant> restaurants = restaurantDao.findAll();
    return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);

}

    public void save (Restaurant restaurant){
        restaurantDao.save(restaurant);
    }
    public Restaurant findById(int id) {
        return restaurantDao.findById(id).get();

    }

    public void update(int id, Restaurant restaurant) {
        restaurantDao.save(restaurant);
    }

    public void deleteById(int id) {
        restaurantDao.deleteById(id);
    }

    public List<RestaurantDto> getRestaurantsSortedByRating() {
        return restaurantDao.findAllByOrderByRatingAsc();
    }
    public List<RestaurantDto> getRestaurantsDateOfPublishAsc() {
        List<Restaurant> restaurants = restaurantDao.findAllByOrderByDateOfPublishAsc();
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }
    public List<RestaurantDto> getRestaurantsDateOfPublishDesc() {
        List<Restaurant> restaurants = restaurantDao.findAllByOrderByDateOfPublishDesc();
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }


    public List<RestaurantDto> getRestaurantsOrderByNameAsc() {
        List<Restaurant> restaurants = restaurantDao.findAllByOrderByRestaurantNameAsc();
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }
    public List<RestaurantDto> getRestaurantsOrderByNameDesc() {
//        return restaurantDao.findAllByOrderByRestaurantNameDesc();
        List<Restaurant> restaurants = restaurantDao.findAllByOrderByRestaurantNameDesc();
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }

    public List<RestaurantDto> getRestaurantsByRatingGreaterThanEqual(double minRating) {
        List<RestaurantDto> restaurants = restaurantDao.findByRatingGreaterThanEqual(minRating);
        return restaurants;
    }

    public List<RestaurantDto> getRestaurantsByType(String type) {

        List<Restaurant> restaurants = restaurantDao.findByType(type);
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }

//    public Page<RestaurantDto> getRestaurantsByType(String type, Pageable pageable) {
//
//        Page<Restaurant> restaurants = restaurantDao.findByType(type,pageable);
//        return restaurants.map(this::convertToDto);
//    }

    public List<RestaurantDto> findByAverageCheckBetween(Double minAvgCheck, Double maxAvgCheck) {
        List<Restaurant> restaurants = restaurantDao.findByAverageCheckBetween(minAvgCheck, maxAvgCheck);
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }

//    public Page<RestaurantDto> getAllRestaurants(Pageable pageable, double minAvgCheck, double maxAvgCheck, String type, Integer minRating) {
//        Page<Restaurant> restaurants = restaurantDao.findByAverageCheckBetweenAndTypeAndRatingGreaterThanEqual(minAvgCheck, maxAvgCheck, type, minRating, pageable);
//        return restaurants.map(this::convertToDto);
//    }
    private RestaurantDto convertToDto(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantDto.class);
    }

    public List<RestaurantDto> getRestaurantsByName(String restaurantName) {
        List<Restaurant> restaurants = restaurantDao.findByRestaurantByName(restaurantName);
//        List<Restaurant> restaurants = restaurantDao.findByRestaurantName(restaurantName);
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }

    public double getAvgRatingByRestaurantId(int id) {
    return restaurantDao.getAvgRatingByRestaurantId(id);
    }
}


