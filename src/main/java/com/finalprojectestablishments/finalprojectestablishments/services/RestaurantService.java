package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.RestaurantDao;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {
    private RestaurantDao restaurantDao;


    public void save (Restaurant restaurant){
        restaurantDao.save(restaurant);
    }
    public Restaurant findById(int id) {
        return restaurantDao.findById(id).get();

    }
}
