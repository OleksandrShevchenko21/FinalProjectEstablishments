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
    Page<Restaurant> findByRatingGreaterThanEqual(@Param("minRating") int minRating,Pageable pageable);

    Page<Restaurant> findByType(String type,Pageable pageable);

    @Query("SELECT new com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto" +
            "(r.id, r.restaurantName, r.type, r.address, r.schedule, r.contacts, r.averageCheck) " +
            "FROM Restaurant r INNER JOIN Review rv ON r.id = rv.restaurant.id GROUP BY r.id HAVING AVG(rv.averageCheck) BETWEEN :minAverageCheck AND :maxAverageCheck")
    List<RestaurantDto> filterByAverageCheckRange(@Param("minAverageCheck") double minAverageCheck, @Param("maxAverageCheck") double maxAverageCheck);

    Page<Restaurant> findByAverageCheckBetween(Double minAvgCheck, Double maxAvgCheck, Pageable pageable);
    Page<Restaurant> findByAverageCheckGreaterThanEqual(Double minAvgCheck, Pageable pageable);
    Page<Restaurant> findByAverageCheckLessThanEqual(Double maxAvgCheck, Pageable pageable);
//    @Query("SELECT new com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto" +
//            "(r.id, r.restaurantName, r.type, r.address, r.schedule, r.contacts, r.averageCheck) " +
//            "FROM Restaurant r JOIN Review rv ON r.id = rv.restaurant.id WHERE r.averageCheck BETWEEN :minAvgCheck AND :maxAvgCheck AND r.type = :type AND rv.rating >= :minRating")
//    Page<Restaurant> findByAverageCheckBetweenAndTypeAndRatingGreaterThanEqual(Double minAvgCheck, Double maxAvgCheck, String type, Integer minRating, Pageable pageable);

}

