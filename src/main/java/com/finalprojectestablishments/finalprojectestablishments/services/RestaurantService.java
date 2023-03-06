package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.RestaurantDao;
import com.finalprojectestablishments.finalprojectestablishments.entity.Booking;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {
    private RestaurantDao restaurantDao;

    public List<Restaurant> findAll() {

        return restaurantDao.findAll();
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

}

