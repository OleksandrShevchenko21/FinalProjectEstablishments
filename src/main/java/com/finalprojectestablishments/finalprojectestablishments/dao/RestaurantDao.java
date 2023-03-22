package com.finalprojectestablishments.finalprojectestablishments.dao;

import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RestaurantDao extends JpaRepository<Restaurant, Integer> {
//    void save(Restaurant restaurant);
//    Optional<Restaurant> findById(int id);
//    void deleteById(int id);

    Page<Restaurant> findAll(Pageable pageable);
//    @Query("SELECT new com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto" +
//            "(r.id, r.restaurantName, r.type, r.address, r.schedule, r.contacts, r.averageCheck) " +
//            "FROM Restaurant r ORDER BY (SELECT AVG(rev.rating) " +
//            "FROM Review rev WHERE rev.restaurant = r) ASC")
//    List<RestaurantDto> findAllByOrderByRatingAsc();

    @Query("SELECT new com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto" +
            "(r.id, r.restaurantName, r.type, r.address, r.schedule, r.contacts, r.averageCheck,r.dateOfPublish,r.averageRating) " +
            "FROM Restaurant r LEFT JOIN r.reviews rev GROUP BY r.id ORDER BY AVG(rev.rating) ASC")
    List<RestaurantDto> findAllByOrderByRatingAsc();


    List<Restaurant> findAllByOrderByDateOfPublishAsc();

    List<Restaurant> findAllByOrderByDateOfPublishDesc();


    //
    List<Restaurant> findAllByOrderByRestaurantNameAsc();

    List<Restaurant> findAllByOrderByRestaurantNameDesc();

    @Query("SELECT new com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto" +
            "(r.id, r.restaurantName, r.type, r.address, r.schedule, r.contacts, r.averageCheck,r.dateOfPublish,r.averageRating) " +
            "FROM Restaurant r JOIN r.reviews rv GROUP BY r.id HAVING AVG(rv.rating) >= :minRating")
    List<RestaurantDto> findByRatingGreaterThanEqual(@Param("minRating") double minRating);


    List<Restaurant> findByType(String type);

    List<Restaurant> findByAverageCheckBetween(Double minAvgCheck, Double maxAvgCheck);

    //    List<Restaurant> findByRestaurantName(String restaurantName);
    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.restaurantName) LIKE :name%")
    List<Restaurant> findByRestaurantByName(@Param("name") String name);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.restaurant.id = :restaurantId")
    Double getAvgRatingByRestaurantId(int restaurantId);

}

