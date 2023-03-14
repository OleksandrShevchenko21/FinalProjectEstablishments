package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import com.finalprojectestablishments.finalprojectestablishments.utils.BuildPage;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.RestaurantsConverter;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@RestController
@RequestMapping("/api/restaurants")
@AllArgsConstructor

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class RestaurantController {

    private RestaurantService restaurantService;
    private RestaurantsConverter restaurantsConverter;
    private BuildPage buildPage;


//    GET /api/restaurants?page=1&size=10&minAvgCheck=10.0&maxAvgCheck=50.0&type=BAR&minRating=4
//
//
//    @GetMapping("")
//    public ResponseEntity<Page<RestaurantDto>> getAllRestaurants(
////            @PageableDefault(page = 0, size = 20,sort = {"id"},
////                    direction = Sort.Direction.ASC) Pageable pageable,
//
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(required = false) Double minAvgCheck,
//            @RequestParam(required = false) Double maxAvgCheck,
//            @RequestParam(required = false) String type,
//            @RequestParam(required = false) Integer minRating,
//            HttpServletResponse response) {
//
//
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<RestaurantDto> restaurants;
//        restaurants = restaurantService.findAll(pageable);
//
////        if (minAvgCheck != null && maxAvgCheck != null) {
////            restaurants = restaurantService.findByAverageCheckBetween(minAvgCheck, maxAvgCheck, pageable);
////        } else if (minAvgCheck != null) {
////            restaurants = restaurantService.findByAverageCheckGreaterThanEqual(minAvgCheck, pageable);
////        } else if (maxAvgCheck != null) {
////            restaurants = restaurantService.findByAverageCheckLessThanEqual(maxAvgCheck, pageable);
////        } else if (type != null) {
////            restaurants = restaurantService.getRestaurantsByType(type, pageable);
////        } else if (minRating != null) {
////            restaurants = restaurantService.getRestaurantsByRatingGreaterThanEqual(minRating, pageable);
////        } else {
////            restaurants = restaurantService.findAll(pageable);
////        }
//////        Page<RestaurantDto> restaurants = restaurantService.getAllRestaurants(pageable, minAvgCheck, maxAvgCheck, type, minRating);
////
////        List<RestaurantDto> restaurantDtoList = restaurants.getContent();
////        Page<RestaurantDto> restaurantDtoPage = new PageImpl<>(restaurantDtoList, pageable, restaurants.getTotalElements());
////        response.setHeader("link", buildPage.buildPageLinkHeader("/api/restaurants", restaurants));
////        return new ResponseEntity<>(restaurantDtoPage, HttpStatus.OK);
////        }
////        if (type != null) {
////            Page<RestaurantDto> restaurantsByType = restaurantService.getRestaurantsByType(type, pageable);
////            response.setHeader("link", buildPage.buildPageLinkHeader("/api/restaurants?type=" + type, restaurantsByType));
////            return new ResponseEntity<>(restaurantsByType, HttpStatus.OK);
////        }
////
////        if (minAvgCheck != null && maxAvgCheck != null) {
////            Page<RestaurantDto> restaurantsByAvgCheck = restaurantService.findByAverageCheckBetween(minAvgCheck, maxAvgCheck, pageable);
////            response.setHeader("link", buildPage.buildPageLinkHeader("/api/restaurants?minAvgCheck=" + minAvgCheck + "&maxAvgCheck=" + maxAvgCheck, restaurantsByAvgCheck));
////            return new ResponseEntity<>(restaurantsByAvgCheck, HttpStatus.OK);
////        }
////
////        if (minAvgCheck != null) {
////            Page<RestaurantDto> restaurantsByMinAvgCheck = restaurantService.findByAverageCheckGreaterThanEqual(minAvgCheck, pageable);
////            response.setHeader("link", buildPage.buildPageLinkHeader("/api/restaurants?minAvgCheck=" + minAvgCheck, restaurantsByMinAvgCheck));
////            return new ResponseEntity<>(restaurantsByMinAvgCheck, HttpStatus.OK);
////        }
////
////        if (maxAvgCheck != null) {
////            Page<RestaurantDto> restaurantsByMaxAvgCheck = restaurantService.findByAverageCheckLessThanEqual(maxAvgCheck, pageable);
////            response.setHeader("link", buildPage.buildPageLinkHeader("/api/restaurants?maxAvgCheck=" + maxAvgCheck, restaurantsByMaxAvgCheck));
////            return new ResponseEntity<>(restaurantsByMaxAvgCheck, HttpStatus.OK);
////        }
////
////        if (minRating != null) {
////            Page<RestaurantDto> restaurantsByMinRating = restaurantService.getRestaurantsByRatingGreaterThanEqual(minRating, pageable);
////            response.setHeader("link", buildPage.buildPageLinkHeader("/api/restaurants?minRating=" + minRating, restaurantsByMinRating));
////            return new ResponseEntity<>(restaurantsByMinRating, HttpStatus.OK);
////        }
//
//        Page<RestaurantDto> allRestaurants = restaurantService.findAll(pageable);
//        response.setHeader("link", buildPage.buildPageLinkHeader("/api/restaurants", allRestaurants));
//        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
//    }
@GetMapping("")
public ResponseEntity<List<RestaurantDto>> getAllRestaurants(
//            @PageableDefault(page = 0, size = 20,sort = {"id"},
//                    direction = Sort.Direction.ASC) Pageable pageable,

        @RequestParam(required = false) Double minAvgCheck,
        @RequestParam(required = false) Double maxAvgCheck,
        @RequestParam(required = false) String type,
        @RequestParam(required = false) Integer minRating,
        HttpServletResponse response) {



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

    //    {{jserver}}/api/restaurants?page=1&size=2&minAvgCheck=50.0&maxAvgCheck=100.0
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
        restaurantService.update(id, restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteById(id);
    }

    @GetMapping("/sorted-by-rating")
    public List<RestaurantDto> getRestaurantsSortedByRating() {
        return restaurantService.getRestaurantsSortedByRating();
    }

//    @GetMapping("/sorted-by-date-of-publish=asc")
//    public List<Restaurant> getRestaurantsSortedByDateOfPublishAsc() {
//        return restaurantService.getRestaurantsDateOfPublishAsc();
//    }
//    @GetMapping("/sorted-by-date-of-publish=desc")
//    public List<Restaurant> getRestaurantsSortedByDateOfPublishDesc() {
//        return restaurantService.getRestaurantsDateOfPublishDesc();
//    }

    @GetMapping("/sorted-by-order-by-name=asc")
    public List<RestaurantDto> getRestaurantsSortedByNameAsc() {
        return restaurantService.getRestaurantsOrderByNameAsc();
    }

    @GetMapping("/sorted-by-order-by-name=desc")
    public List<RestaurantDto> getRestaurantsSortedByNameDesc() {
        return restaurantService.getRestaurantsOrderByNameDesc();
    }

    @GetMapping("/filter/average-rating")
//    {{jserver}}/api/restaurants/filter/average-rating?minRating=60
    public List<RestaurantDto> getRestaurantsByRatingGreaterThanEqual(@RequestParam int minRating) {
        return restaurantService.getRestaurantsByRatingGreaterThanEqual(minRating);
    }

    @GetMapping("/filter/type")
//    {{jserver}}/api/restaurants/filter/type?type=BAR
    public List<RestaurantDto> getRestaurantsByType(@RequestParam String type) {
        return restaurantService.getRestaurantsByType(type);
    }
    @GetMapping("/filter/average-check/between")
//    {{jserver}}/api/restaurants/filter/average-check/between?minAvgCheck=50.0&maxAvgCheck=100.0
    public List<RestaurantDto> getRestaurantsByAverageCheckBetween(@RequestParam Double minAvgCheck, Double maxAvgCheck) {
        return restaurantService.findByAverageCheckBetween(minAvgCheck,maxAvgCheck);
    }
    @GetMapping("/filter/average-check/greater")
//    {{jserver}}/api/restaurants/filter/average-check/greater?minAvgCheck=50.0
    public List<RestaurantDto> getRestaurantsByAverageCheckGreaterThanEqual(@RequestParam Double minAvgCheck) {
        return restaurantService.findByAverageCheckGreaterThanEqual(minAvgCheck);
    }
    @GetMapping("/filter/average-check/less")
//    {{jserver}}/api/restaurants/filter/average-check/greater?maxAvgCheck=50.0
    public List<RestaurantDto> getRestaurantsByAverageCheckLessThanEqual(@RequestParam Double maxAvgCheck) {
        return restaurantService.findByAverageCheckLessThanEqual(maxAvgCheck);
    }

}
