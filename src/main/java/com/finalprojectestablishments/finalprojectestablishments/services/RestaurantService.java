package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.RestaurantDao;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {
    private RestaurantDao restaurantDao;

public Page<Restaurant> findAll(Pageable pageable) {
    return restaurantDao.findAll(pageable);
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

    public List<Restaurant> getRestaurantsSortedByRating() {
        return restaurantDao.findAllByOrderByRatingAsc();
    }
//    public List<Restaurant> getRestaurantsDateOfPublishAsc() {
//        return restaurantDao.findAllByOrderByDateOfPublishAsc();
//    }
//    public List<Restaurant> getRestaurantsDateOfPublishDesc() {
//        return restaurantDao.findAllByOrderByDateOfPublishDesc();
//    }


    public List<Restaurant> getRestaurantsOrderByNameAsc() {
    return restaurantDao.findAllByOrderByRestaurantNameAsc();
    }
    public List<Restaurant> getRestaurantsOrderByNameDesc() {
        return restaurantDao.findAllByOrderByRestaurantNameDesc();
    }

    public List<Restaurant> getRestaurantsByRatingGreaterThanEqual(int minRating) {
    return restaurantDao.findByRatingGreaterThanEqual(minRating);
    }

    public List<Restaurant> getRestaurantsByType(String type) {
    return restaurantDao.findByType(type);
    }

    public Page<Restaurant> findByAverageCheckBetween(Double minAvgCheck, Double maxAvgCheck, Pageable pageable) {
        return restaurantDao.findByAverageCheckBetween(minAvgCheck, maxAvgCheck, pageable);
    }

    public Page<Restaurant> findByAverageCheckGreaterThanEqual(Double minAvgCheck, Pageable pageable) {
        return restaurantDao.findByAverageCheckGreaterThanEqual(minAvgCheck, pageable);
    }

    public Page<Restaurant> findByAverageCheckLessThanEqual(Double maxAvgCheck, Pageable pageable) {
        return restaurantDao.findByAverageCheckLessThanEqual(maxAvgCheck, pageable);
    }
}


