package com.finalprojectestablishments.finalprojectestablishments.dao;

import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantDao extends JpaRepository<Restaurant, Integer> {

    Page<Restaurant> findAll(Pageable pageable);
    @Query("SELECT new com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto" +
            "(r.id, r.restaurantName, r.type, r.address, r.schedule, r.contacts, r.averageCheck) " +
            "FROM Restaurant r ORDER BY (SELECT AVG(rev.rating) " +
            "FROM Review rev WHERE rev.restaurant = r) ASC")
    List<RestaurantDto> findAllByOrderByRatingAsc();


    //    List<Restaurant> findAllByOrderByDateOfPublishAsc();
//    List<Restaurant> findAllByOrderByDateOfPublishDesc();
//
    List<Restaurant> findAllByOrderByRestaurantNameAsc();
    List<Restaurant> findAllByOrderByRestaurantNameDesc();

    @Query("SELECT new com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto" +
            "(r.id, r.restaurantName, r.type, r.address, r.schedule, r.contacts, r.averageCheck) " +
            "FROM Restaurant r JOIN r.reviews rv GROUP BY r.id HAVING AVG(rv.rating) >= :minRating")
    List<RestaurantDto> findByRatingGreaterThanEqual(@Param("minRating") int minRating);


    List<Restaurant> findByType(String type);

    List<Restaurant> findByAverageCheckBetween(Double minAvgCheck, Double maxAvgCheck);
    List<Restaurant> findByAverageCheckGreaterThanEqual(Double minAvgCheck);
    List<Restaurant> findByAverageCheckLessThanEqual(Double maxAvgCheck);

}

