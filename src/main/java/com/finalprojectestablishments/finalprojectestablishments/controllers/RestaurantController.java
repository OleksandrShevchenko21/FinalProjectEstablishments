package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import com.finalprojectestablishments.finalprojectestablishments.utils.BuildPage;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.RestaurantsConverter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private RestaurantService restaurantService;
    private RestaurantsConverter restaurantsConverter;
    private BuildPage buildPage;

    //    @GetMapping("")
//    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
//        List<Restaurant> restaurants = restaurantService.findAll();
//        List<RestaurantDto> restaurantDtoList = restaurantsConverter.resaturantListToRestaurantDtoList(restaurants);
//        return new ResponseEntity<>(restaurantDtoList,HttpStatusCode.valueOf(200));
//
//    }
    @GetMapping("")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants(@RequestParam(defaultValue = "1") int page,
                                                                 @RequestParam(defaultValue = "2") int size,
                                                                 @RequestParam(required = false) Double minAvgCheck,
                                                                 @RequestParam(required = false) Double maxAvgCheck,

                                                                 HttpServletResponse response) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Restaurant> restaurants = restaurantService.findAll(pageable);


        if (minAvgCheck != null && maxAvgCheck != null) {
            restaurants = restaurantService.findByAverageCheckBetween(minAvgCheck, maxAvgCheck, pageable);
        } else if (minAvgCheck != null) {
            restaurants = restaurantService.findByAverageCheckGreaterThanEqual(minAvgCheck, pageable);
        } else if (maxAvgCheck != null) {
            restaurants = restaurantService.findByAverageCheckLessThanEqual(maxAvgCheck, pageable);
        } else {
            restaurants = restaurantService.findAll(pageable);
        }


        List<RestaurantDto> restaurantDtoList = restaurantsConverter.resaturantListToRestaurantDtoList(restaurants.getContent());
        Page<RestaurantDto> restaurantDtoPage = new PageImpl<>(restaurantDtoList, pageable, restaurants.getTotalElements());
        response.setHeader("link", buildPage.buildPageLinkHeader("/api/restaurants", restaurantDtoPage));
        return new ResponseEntity<>(restaurantDtoList, HttpStatus.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getOneRestaurant(@PathVariable int id) {
        Restaurant restaurant = restaurantService.findById(id);
        RestaurantDto restaurantDto = restaurantsConverter.restaurantToRestaurantDto(restaurant);
        return new ResponseEntity<>(restaurantDto, HttpStatusCode.valueOf(200));
    }

    //    {{jserver}}/api/restaurants?page=1&size=2&minAvgCheck=50.0&maxAvgCheck=100.0
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRestaurant(@RequestBody RestaurantDto restaurantdto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantdto.getRestaurantName());
        restaurant.setType(restaurantdto.getType());
        restaurant.setAddress(restaurantdto.getAddress());
        restaurant.setSchedule(restaurantdto.getSchedule());
        restaurant.setContacts(restaurantdto.getContacts());
        restaurant.setAverageCheck(restaurantdto.getAverageCheck());
//        restaurant.setReviews(restaurantdto.getReviews().stream()
//                .map(m -> {
//                    Review r = new Review();
//                    r.setComment(m.getComment());
//                    r.setRating(m.getRating());
////                    r.getUser().getId();
//                    return r;
//                }).collect(Collectors.toList()));
        restaurant.setReviews(restaurantdto.getReviews());

        restaurantService.save(restaurant);
    }

    @PatchMapping("/{id}")
    public void updateRestaurant(@PathVariable int id, @RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantService.findById(id);
        restaurant.setRestaurantName(restaurantDto.getRestaurantName());
        restaurant.setType(restaurantDto.getType());
        restaurant.setAverageCheck(restaurantDto.getAverageCheck());
        restaurantService.update(id, restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteById(id);
    }

    @GetMapping("/sorted-by-rating")
    public List<Restaurant> getRestaurantsSortedByRating() {
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
    public List<Restaurant> getRestaurantsSortedByNameAsc() {
        return restaurantService.getRestaurantsOrderByNameAsc();
    }

    @GetMapping("/sorted-by-order-by-name=desc")
    public List<Restaurant> getRestaurantsSortedByNameDesc() {
        return restaurantService.getRestaurantsOrderByNameDesc();
    }

    @GetMapping("/filter/average-rating")
//    {{jserver}}/api/restaurants/filter/average-rating?minRating=60
    public List<Restaurant> getRestaurantsByRatingGreaterThanEqual(@RequestParam int minRating) {
        return restaurantService.getRestaurantsByRatingGreaterThanEqual(minRating);
    }

    @GetMapping("/filter/type")
//    {{jserver}}/api/restaurants/filter/type?type=BAR
    public List<Restaurant> getRestaurantsByType(@RequestParam String type) {
        return restaurantService.getRestaurantsByType(type);
    }

//    @GetMapping("/restaurants/filter/rangeAverageCheck")
//    public List<Restaurant> filterRestaurants(@RequestParam(required = false) Integer minAverageCheck,
//                                              @RequestParam(required = false) Integer maxAverageCheck) {
//
//         if (minAverageCheck != null && maxAverageCheck != null) {
//            return restaurantService.filterByAverageCheckRange(minAverageCheck, maxAverageCheck);
//        } else {
//            return restaurantService.findAll(Pageable pageable);
//        }
//    }

}
