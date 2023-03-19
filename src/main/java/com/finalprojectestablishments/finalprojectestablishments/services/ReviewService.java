package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.UserDao;
import com.finalprojectestablishments.finalprojectestablishments.dao.RestaurantDao;
import com.finalprojectestablishments.finalprojectestablishments.dao.ReviewDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.ReviewDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewDao reviewDao;

    public List<Review> findAll() {
        return reviewDao.findAll();
    }

    public List<Review> findByRestaurantId(int restaurantId) {
        return reviewDao.findByRestaurantId(restaurantId);
    }


    public Review findById(int id) {
        return reviewDao.findById(id).get();
    }

    public void save(Review review) {

        reviewDao.save(review);
    }

    public void update(int id, Review review) {
        reviewDao.save(review);
    }

    public void delete(int id) {
        reviewDao.deleteById(id);
    }
}
