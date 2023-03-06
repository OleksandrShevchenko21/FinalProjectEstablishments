package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dao.RestaurantDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@AllArgsConstructor
public class RestaurantController {
    private RestaurantDao restaurantDao;
    private RestaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> all = restaurantDao.findAll();
        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getOneEstablishment(@PathVariable int id) {
        Restaurant restaurant = restaurantDao.findById(id).get();
        return new ResponseEntity<>(restaurant, HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.save(restaurant);
    }

    @PatchMapping("/{id}")
    public void updateRestaurant(@PathVariable int id, @RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantDao.findById(id).get();
        restaurant.setName(restaurantDto.getRestaurantName());
        restaurantDao.save(restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable int id) {
        restaurantDao.deleteById(id);
    }

}
