package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.FavoritesRestaurantDao;
import com.finalprojectestablishments.finalprojectestablishments.entity.FavoritesRestaurant;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
    @AllArgsConstructor
    public class FavoritesRestaurantService {
        private FavoritesRestaurantDao favoritesRestaurantDao;

        public Page<FavoritesRestaurant> findAll(Pageable pageable) {
            return favoritesRestaurantDao.findAll(pageable);
        }
        public List<FavoritesRestaurant> findByUserId(int userId) {
            return  favoritesRestaurantDao.findByUserId(userId);
        }

        public FavoritesRestaurant findById(int id) {
            return favoritesRestaurantDao.findById(id).get();

        }
        public void save (FavoritesRestaurant favoritesRestaurant){
            favoritesRestaurantDao.save(favoritesRestaurant);
        }


        public void update(int id, FavoritesRestaurant favoritesRestaurant) {
            favoritesRestaurantDao.save(favoritesRestaurant);
        }

        public void deleteById(int id) {
            favoritesRestaurantDao.deleteById(id);
        }


    }

