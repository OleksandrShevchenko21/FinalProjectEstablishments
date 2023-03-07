package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import com.finalprojectestablishments.finalprojectestablishments.utils.RestaurantsConverter;
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

    private RestaurantService restaurantService;
    private RestaurantsConverter restaurantsConverter;

    @GetMapping("")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.findAll();
        List<RestaurantDto> restaurantDtoList = restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
        return new ResponseEntity<>(restaurantDtoList,HttpStatusCode.valueOf(200));

    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getOneRestaurant(@PathVariable int id) {
        Restaurant restaurant = restaurantService.findById(id);
        RestaurantDto restaurantDto = restaurantsConverter.restaurantToRestaurantDto(restaurant);
        return new ResponseEntity<>(restaurantDto, HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRestaurant(@RequestBody RestaurantDto restaurantdto) {
        Restaurant restaurant =new Restaurant();
        restaurant.setRestaurantName(restaurantdto.getRestaurantName());
        restaurantService.save(restaurant);
    }

    @PatchMapping("/{id}")
    public void updateRestaurant(@PathVariable int id, @RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantService.findById(id);
        restaurant.setRestaurantName(restaurantDto.getRestaurantName());
        restaurantService.update(id,restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteById(id);
    }

}
