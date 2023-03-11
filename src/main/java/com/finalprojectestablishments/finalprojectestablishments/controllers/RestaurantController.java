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
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
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

//    GET /api/users?page=2&size=10&sort=name&filter=active HTTP/1.1
//    Host: example.com
//    Authorization: Bearer <token>
//    Accept: application/json
//    api/restaurants?page=1&size=10&minAvgCheck=10&maxAvgCheck=500&type=BAR&minRating=3 HTTP/1.1
//    GET /api/restaurants?page=1&size=10&minAvgCheck=10.0&maxAvgCheck=50.0&type=BAR&minRating=4

    //    @GetMapping(path = "/characters/page")
//    Page<MovieCharacter> loadCharactersPage(
//            @PageableDefault(page = 0, size = 20)
//            @SortDefault.SortDefaults({
//                    @SortDefault(sort = "name", direction = Sort.Direction.DESC),
//                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
//            })
//            Pageable pageable) {
//        return characterRepository.findAllPage(pageable);
//    }
    @GetMapping("")
    public ResponseEntity<Page<RestaurantDto>> getAllRestaurants(
//            @PageableDefault(page = 0, size = 20,sort = {"id"},
//                    direction = Sort.Direction.ASC) Pageable pageable,

            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Double minAvgCheck,
            @RequestParam(required = false) Double maxAvgCheck,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer minRating,
            HttpServletResponse response) {


        Pageable pageable = PageRequest.of(page - 1, size);
        Page<RestaurantDto> restaurants;


        if (minAvgCheck != null && maxAvgCheck != null) {
            restaurants = restaurantService.findByAverageCheckBetween(minAvgCheck, maxAvgCheck, pageable);
        } else if (minAvgCheck != null) {
            restaurants = restaurantService.findByAverageCheckGreaterThanEqual(minAvgCheck, pageable);
        } else if (maxAvgCheck != null) {
            restaurants = restaurantService.findByAverageCheckLessThanEqual(maxAvgCheck, pageable);
        } else if (type != null) {
            restaurants = restaurantService.getRestaurantsByType(type, pageable);
        } else if (minRating != null) {
            restaurants = restaurantService.getRestaurantsByRatingGreaterThanEqual(minRating, pageable);
        } else {
            restaurants = restaurantService.findAll(pageable);
        }
//        Page<RestaurantDto> restaurants = restaurantService.getAllRestaurants(pageable, minAvgCheck, maxAvgCheck, type, minRating);

        List<RestaurantDto> restaurantDtoList = restaurants.getContent();
        Page<RestaurantDto> restaurantDtoPage = new PageImpl<>(restaurantDtoList, pageable, restaurants.getTotalElements());
        response.setHeader("link", buildPage.buildPageLinkHeader("/api/restaurants", restaurants));
        return new ResponseEntity<>(restaurantDtoPage, HttpStatus.OK);
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

//    @GetMapping("/filter/average-rating")
////    {{jserver}}/api/restaurants/filter/average-rating?minRating=60
//    public List<RestaurantDto> getRestaurantsByRatingGreaterThanEqual(@RequestParam int minRating) {
//        return restaurantService.getRestaurantsByRatingGreaterThanEqual(minRating);
//    }

//    @GetMapping("/filter/type")
////    {{jserver}}/api/restaurants/filter/type?type=BAR
//    public List<RestaurantDto> getRestaurantsByType(@RequestParam String type) {
//        return restaurantService.getRestaurantsByType(type);
//    }

}
