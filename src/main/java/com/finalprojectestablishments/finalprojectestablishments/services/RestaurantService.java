package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.RestaurantDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.RestaurantsConverter;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantService {
    private RestaurantDao restaurantDao;
    private RestaurantsConverter restaurantsConverter;
    private final ModelMapper modelMapper;

public Page<RestaurantDto> findAll(Pageable pageable) {
    Page<Restaurant> restaurants = restaurantDao.findAll(pageable);
    return restaurants.map(this::convertToDto);

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
//    public List<Restaurant> getRestaurantsDateOfPublishAsc() {
//        return restaurantDao.findAllByOrderByDateOfPublishAsc();
//    }
//    public List<Restaurant> getRestaurantsDateOfPublishDesc() {
//        return restaurantDao.findAllByOrderByDateOfPublishDesc();
//    }


    public List<RestaurantDto> getRestaurantsOrderByNameAsc() {
        List<Restaurant> restaurants = restaurantDao.findAllByOrderByRestaurantNameAsc();
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }
    public List<RestaurantDto> getRestaurantsOrderByNameDesc() {
//        return restaurantDao.findAllByOrderByRestaurantNameDesc();
        List<Restaurant> restaurants = restaurantDao.findAllByOrderByRestaurantNameDesc();
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }

    public Page<RestaurantDto> getRestaurantsByRatingGreaterThanEqual(int minRating, Pageable pageable) {
        Page<Restaurant> restaurants = restaurantDao.findByRatingGreaterThanEqual(minRating,pageable);
        return restaurants.map(this::convertToDto);
    }

//    public List<RestaurantDto> getRestaurantsByType(String type) {
//
//        List<Restaurant> restaurants = restaurantDao.findByType(type);
//        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
//    }

    public Page<RestaurantDto> getRestaurantsByType(String type, Pageable pageable) {

        Page<Restaurant> restaurants = restaurantDao.findByType(type,pageable);
        return restaurants.map(this::convertToDto);
    }

    public Page<RestaurantDto> findByAverageCheckBetween(Double minAvgCheck, Double maxAvgCheck, Pageable pageable) {
        Page<Restaurant> restaurants = restaurantDao.findByAverageCheckBetween(minAvgCheck, maxAvgCheck, pageable);
        return restaurants.map(this::convertToDto);
    }


    public Page<RestaurantDto> findByAverageCheckGreaterThanEqual(Double minAvgCheck, Pageable pageable) {
        Page<Restaurant> restaurants = restaurantDao.findByAverageCheckGreaterThanEqual(minAvgCheck, pageable);
        return restaurants.map(this::convertToDto);

    }

    public Page<RestaurantDto> findByAverageCheckLessThanEqual(Double maxAvgCheck, Pageable pageable) {
        Page<Restaurant> restaurants = restaurantDao.findByAverageCheckLessThanEqual(maxAvgCheck, pageable);
        return restaurants.map(this::convertToDto);

    }
//    public Page<RestaurantDto> getAllRestaurants(Pageable pageable, Double minAvgCheck, Double maxAvgCheck, String type, Integer minRating) {
//        Page<Restaurant> restaurants = restaurantDao.findByAverageCheckBetweenAndTypeAndRatingGreaterThanEqual(minAvgCheck, maxAvgCheck, type, minRating, pageable);
//        return restaurants.map(this::convertToDto);
//    }
    private RestaurantDto convertToDto(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantDto.class);
    }

}


