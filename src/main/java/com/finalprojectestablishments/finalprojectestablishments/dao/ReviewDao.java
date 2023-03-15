package com.finalprojectestablishments.finalprojectestablishments.dao;

import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewDao extends JpaRepository<Review, Integer> {
    List<Review> findByRestaurantId(int restaurantId);
//    Optional<Review> findById(int id);
//
//    void save(Review review);
//
//    void deleteById(int id);
}
