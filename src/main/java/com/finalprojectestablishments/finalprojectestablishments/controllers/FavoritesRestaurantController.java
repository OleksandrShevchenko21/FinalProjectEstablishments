package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.FavoritesRestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.FavoritesRestaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.services.FavoritesRestaurantService;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import com.finalprojectestablishments.finalprojectestablishments.services.UserService;
import com.finalprojectestablishments.finalprojectestablishments.utils.BuildPage;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.FavoritesRestaurantConverter;
import lombok.AllArgsConstructor;

import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites/user")
@AllArgsConstructor
public class FavoritesRestaurantController {

    private FavoritesRestaurantService favoritesRestaurantService;
    private FavoritesRestaurantConverter favoritesRestaurantConverter;
    private UserService userService;
    private RestaurantService restaurantService;
    private BuildPage buildPage;

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoritesRestaurantDto>> getAllRestaurants(@RequestParam(defaultValue = "1") int page,
                                                                          @RequestParam(defaultValue = "5") int size,
                                                                          @PathVariable int userId) {

        List<FavoritesRestaurant> restaurants = favoritesRestaurantService.findByUserId(userId);
        List<FavoritesRestaurantDto> restaurantDtoList = favoritesRestaurantConverter.favoritesResaturantListToRestaurantDtoList(restaurants);
        return new ResponseEntity<>(restaurantDtoList, HttpStatus.valueOf(200));
    }

    @GetMapping("/{userId}/{id}")
    public ResponseEntity<FavoritesRestaurantDto> getOneRestaurant(@PathVariable int id,
                                                                   @PathVariable int userId) {
        FavoritesRestaurant favoritesRestaurant = favoritesRestaurantService.findById(id);
        FavoritesRestaurantDto favoritesRestaurantDto = favoritesRestaurantConverter.favoritesRestaurantToRestaurantDto(favoritesRestaurant);
        return new ResponseEntity<>(favoritesRestaurantDto, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/{userId}/{restaurantId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRestaurant(@PathVariable int userId,
                               @PathVariable int restaurantId) {
        FavoritesRestaurant favoritesRestaurant = new FavoritesRestaurant();
        User user = userService.findById(userId);
        Restaurant restaurant = restaurantService.findById(restaurantId);
        favoritesRestaurant.setUser(user);
        favoritesRestaurant.setRestaurant(restaurant);
        favoritesRestaurant.setRestaurantName(restaurant.getRestaurantName());
        favoritesRestaurantService.save(favoritesRestaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable int id) {
        favoritesRestaurantService.deleteById(id);
    }

}
