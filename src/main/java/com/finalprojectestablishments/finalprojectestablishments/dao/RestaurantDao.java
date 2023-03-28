package com.finalprojectestablishments.finalprojectestablishments.dao;

import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RestaurantDao extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r LEFT JOIN r.reviews rev GROUP BY r.id ORDER BY AVG(rev.rating) ASC")
    List<Restaurant> findAllByOrderByRatingAsc();

    @Query("SELECT r FROM Restaurant r LEFT JOIN r.reviews rev GROUP BY r.id ORDER BY AVG(rev.rating) DESC")
    List<Restaurant> findAllByOrderByRatingDesc();


    List<Restaurant> findAllByOrderByDateOfPublishAsc();

    List<Restaurant> findAllByOrderByDateOfPublishDesc();


    List<Restaurant> findAllByOrderByRestaurantNameAsc();

    List<Restaurant> findAllByOrderByRestaurantNameDesc();

    @Query("SELECT r FROM Restaurant r JOIN r.reviews rv GROUP BY r.id HAVING AVG(rv.rating) >= :minRating")
    List<Restaurant> findByRatingGreaterThanEqual(@Param("minRating") double minRating);


    List<Restaurant> findByType(String type);

    List<Restaurant> findByAverageCheckBetween(Double minAvgCheck, Double maxAvgCheck);

    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.restaurantName) LIKE :name%")
    List<Restaurant> findByRestaurantByName(@Param("name") String name);

    @Query("SELECT ROUND(AVG(r.rating), 1) FROM Review r WHERE r.restaurant.id = :restaurantId")
    Double getAvgRatingByRestaurantId(int restaurantId);

    @Query("SELECT r FROM Restaurant r JOIN r.users u WHERE u.userName = :userName")
    List<Restaurant> findFavoriteRestaurantsByUserName(@Param("userName") String userName);

}

