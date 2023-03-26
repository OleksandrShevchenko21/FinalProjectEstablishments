package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import com.finalprojectestablishments.finalprojectestablishments.services.UserService;
import com.finalprojectestablishments.finalprojectestablishments.utils.BuildPage;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.RestaurantsConverter;

import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@AllArgsConstructor

public class RestaurantController {

    private RestaurantService restaurantService;
    private UserService userService;
    private RestaurantsConverter restaurantsConverter;
    private BuildPage buildPage;


    @GetMapping("")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        List<RestaurantDto> restaurants;
        restaurants = restaurantService.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getOneRestaurant(@PathVariable int id) {
        Restaurant restaurant = restaurantService.findById(id);
        RestaurantDto restaurantDto = restaurantsConverter.restaurantToRestaurantDto(restaurant);
        return new ResponseEntity<>(restaurantDto, HttpStatus.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRestaurant(@RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantDto.getRestaurantName());
        restaurant.setType(restaurantDto.getType());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setSchedule(restaurantDto.getSchedule());
        restaurant.setContacts(restaurantDto.getContacts());
        restaurant.setAverageCheck(restaurantDto.getAverageCheck());
        restaurant.setDateOfPublish(LocalDate.now());
        restaurantService.save(restaurant);
    }

    @PatchMapping("/{id}")
    public void updateRestaurant(@PathVariable int id, @RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantService.findById(id);
        restaurant.setRestaurantName(restaurantDto.getRestaurantName());
        restaurant.setType(restaurantDto.getType());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setSchedule(restaurantDto.getSchedule());
        restaurant.setContacts(restaurantDto.getContacts());
        restaurant.setAverageCheck(restaurantDto.getAverageCheck());
        restaurant.setDateOfPublish(restaurantDto.getDateOfPublish());
        restaurantService.update(id, restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteById(id);
    }

    @GetMapping("/sorted-by-rating-asc")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsSortedByRatingAsc() {
        List<RestaurantDto> restaurants = restaurantService.getRestaurantsSortedByRatingAsc();
        return new ResponseEntity<>(restaurants, HttpStatus.valueOf(200));
    }

    @GetMapping("/sorted-by-rating-desc")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsSortedByRatingDesc() {
        List<RestaurantDto> restaurants = restaurantService.getRestaurantsSortedByRatingDesc();
        return new ResponseEntity<>(restaurants, HttpStatus.valueOf(200));
    }

    @GetMapping("/sorted-by-date-of-publish=asc")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsSortedByDateOfPublishAsc() {
        List<RestaurantDto> restaurants = restaurantService.getRestaurantsDateOfPublishAsc();
        return new ResponseEntity<>(restaurants, HttpStatus.valueOf(200));
    }

    @GetMapping("/sorted-by-date-of-publish=desc")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsSortedByDateOfPublishDesc() {
        List<RestaurantDto> restaurants = restaurantService.getRestaurantsDateOfPublishDesc();
        return new ResponseEntity<>(restaurants, HttpStatus.valueOf(200));
    }

    @GetMapping("/sorted-by-order-by-name/asc")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsSortedByNameAsc() {
        List<RestaurantDto> restaurants = restaurantService.getRestaurantsOrderByNameAsc();
        return new ResponseEntity<>(restaurants, HttpStatus.valueOf(200));
    }

    @GetMapping("/sorted-by-order-by-name/desc")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsSortedByNameDesc() {
        List<RestaurantDto> restaurants = restaurantService.getRestaurantsOrderByNameDesc();
        return new ResponseEntity<>(restaurants, HttpStatus.valueOf(200));
    }

    @GetMapping("/filter/average-rating")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByRatingGreaterThanEqual(@RequestParam double minRating) {
        List<RestaurantDto> restaurants=restaurantService.getRestaurantsByRatingGreaterThanEqual(minRating);
        return new ResponseEntity<>(restaurants, HttpStatus.valueOf(200));
    }

    @GetMapping("/filter/type")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByType(@RequestParam String type) {
        List<RestaurantDto> restaurants=restaurantService.getRestaurantsByType(type);
        return new ResponseEntity<>(restaurants, HttpStatus.valueOf(200));
    }

    @GetMapping("/filter/average-check/between")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByAverageCheckBetween(@RequestParam Double minAvgCheck, Double maxAvgCheck) {
        List<RestaurantDto> restaurants=restaurantService.findByAverageCheckBetween(minAvgCheck,maxAvgCheck);
        return new ResponseEntity<>(restaurants, HttpStatus.valueOf(200));
    }

    @GetMapping("/filter/name")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByName(@RequestParam String restaurantName) {
        List<RestaurantDto> restaurants=restaurantService.getRestaurantsByName(restaurantName);
        return new ResponseEntity<>(restaurants, HttpStatus.valueOf(200));
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByUserName(@PathVariable String userName) {
        List<RestaurantDto> restaurants = restaurantService.getRestaurantsByUserName(userName);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PostMapping("/{id}/username/{userName}")
    public void addRestaurantsToFavoritesByUser(@PathVariable int id,
                                                @PathVariable String userName,
                                                @RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantService.findById(id);
        User user = userService.findByUserName(userName);
        if (!user.getFavoritesRestaurants().contains(restaurant)) {

            user.getFavoritesRestaurants().add(restaurant);
            userService.save(user);
        } else {
            throw new IllegalArgumentException("Restaurant already exists in user's favorites.");
        }
    }

    @DeleteMapping("/{id}/delete/{userName}")
    public void deleteRestaurantsFromFavoritesByUser(@PathVariable int id,
                                                     @PathVariable String userName
    ) {
        Restaurant restaurant = restaurantService.findById(id);
        User user = userService.findByUserName(userName);
        if (user.getFavoritesRestaurants().contains(restaurant)) {
            user.getFavoritesRestaurants().remove(restaurant);
            userService.save(user);
        } else {
            throw new IllegalArgumentException("Could not be removed.");
        }
    }
}
