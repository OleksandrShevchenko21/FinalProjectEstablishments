package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.RestaurantDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.RestaurantsConverter;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {
    private RestaurantDao restaurantDao;
    private RestaurantsConverter restaurantsConverter;
    private final ModelMapper modelMapper;

    public List<RestaurantDto> findAll() {
        List<Restaurant> restaurants = restaurantDao.findAll();
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);

    }

    public void save(Restaurant restaurant) {
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

    public List<RestaurantDto> getRestaurantsSortedByRatingAsc() {
        List<Restaurant> restaurants = restaurantDao.findAllByOrderByRatingAsc();
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }

    public List<RestaurantDto> getRestaurantsSortedByRatingDesc() {
        List<Restaurant> restaurants = restaurantDao.findAllByOrderByRatingDesc();
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
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
        List<Restaurant> restaurants = restaurantDao.findAllByOrderByRestaurantNameDesc();
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }

    public List<RestaurantDto> getRestaurantsByRatingGreaterThanEqual(double minRating) {
        List<Restaurant> restaurants = restaurantDao.findByRatingGreaterThanEqual(minRating);
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }

    public List<RestaurantDto> getRestaurantsByType(String type) {
        List<Restaurant> restaurants = restaurantDao.findByType(type);
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }

    public List<RestaurantDto> findByAverageCheckBetween(Double minAvgCheck, Double maxAvgCheck) {
        List<Restaurant> restaurants = restaurantDao.findByAverageCheckBetween(minAvgCheck, maxAvgCheck);
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }

    public List<RestaurantDto> getRestaurantsByName(String restaurantName) {
        List<Restaurant> restaurants = restaurantDao.findByRestaurantByName(restaurantName);
        return restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
    }

    public double getAvgRatingByRestaurantId(int id) {
        return restaurantDao.getAvgRatingByRestaurantId(id);
    }

    public List<RestaurantDto> getRestaurantsByUserName(String userName) {
        List<Restaurant> restaurants = restaurantDao.findFavoriteRestaurantsByUserName(userName);
        List<RestaurantDto> favoriteRestaurants = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            RestaurantDto restaurantDto = restaurantsConverter.restaurantToRestaurantDto(restaurant);
            favoriteRestaurants.add(restaurantDto);
        }
        return favoriteRestaurants;
    }
}


