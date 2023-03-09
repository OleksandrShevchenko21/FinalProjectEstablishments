package com.finalprojectestablishments.finalprojectestablishments.dao;

import com.finalprojectestablishments.finalprojectestablishments.entity.FavoritesRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesRestaurantDao extends JpaRepository<FavoritesRestaurant, Integer> {
    List<FavoritesRestaurant> findByUserId(int userId);
}
