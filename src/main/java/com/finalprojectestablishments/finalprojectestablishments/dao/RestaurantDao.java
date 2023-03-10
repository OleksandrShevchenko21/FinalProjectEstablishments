package com.finalprojectestablishments.finalprojectestablishments.dao;

import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantDao extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT r FROM Restaurant r ORDER BY (SELECT AVG(rev.rating) FROM Review rev WHERE rev.restaurant = r) asc ")
    List<Restaurant> findAllByOrderByRatingAsc();

//    List<Restaurant> findAllByOrderByDateOfPublishAsc();
//    List<Restaurant> findAllByOrderByDateOfPublishDesc();
//
    List<Restaurant> findAllByOrderByRestaurantNameAsc();
    List<Restaurant> findAllByOrderByRestaurantNameDesc();

//    @Query("SELECT r FROM Restaurant r JOIN r.reviews rv WHERE rv.rating >= :minRating GROUP BY r.id HAVING AVG(rv.rating) >= :minRating")
//@Query("SELECT r FROM Restaurant r JOIN r.reviews rv WHERE rv.rating >= :minRating GROUP BY r.id HAVING AVG(rv.rating) >= :minRating")
@Query("SELECT r FROM Restaurant r JOIN r.reviews rv GROUP BY r.id HAVING AVG(rv.rating) >= :minRating")
    List<Restaurant> findByRatingGreaterThanEqual(@Param("minRating") int minRating);

    List<Restaurant> findByType(String type);

    @Query("SELECT r FROM Restaurant r INNER JOIN Review rv ON r.id = rv.restaurant.id GROUP BY r.id HAVING AVG(rv.averageCheck) BETWEEN :minAverageCheck AND :maxAverageCheck")
    List<Restaurant> filterByAverageCheckRange(@Param("minAverageCheck") double minAverageCheck, @Param("maxAverageCheck") double maxAverageCheck);

    Page<Restaurant> findByAverageCheckBetween(Double minAvgCheck, Double maxAvgCheck, Pageable pageable);
    Page<Restaurant> findByAverageCheckGreaterThanEqual(Double minAvgCheck, Pageable pageable);
    Page<Restaurant> findByAverageCheckLessThanEqual(Double maxAvgCheck, Pageable pageable);

}

