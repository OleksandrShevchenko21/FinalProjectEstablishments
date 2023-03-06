package com.finalprojectestablishments.finalprojectestablishments.dao;

import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantDao extends JpaRepository<Restaurant, Integer> {
}
