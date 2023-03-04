package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.ClientDao;
import com.finalprojectestablishments.finalprojectestablishments.dao.EstablishmentDao;
import com.finalprojectestablishments.finalprojectestablishments.dao.ReviewDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.ReviewDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Client;
import com.finalprojectestablishments.finalprojectestablishments.entity.Establishment;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
//@NoArgsConstructor
public class ReviewService {

    private final ReviewDao reviewDao;
    private final EstablishmentDao establishmentDao;
    private final ClientDao clientDao;


    public ReviewService(ReviewDao reviewDao,ClientDao clientDao, EstablishmentDao establishmentDao) {
        this.reviewDao = reviewDao;
        this.clientDao = clientDao;
        this.establishmentDao = establishmentDao;
    }

    public void save(int clientId, int establishmentId,ReviewDto reviewDto) {

        Review review = new Review();
        Client client = clientDao.findById(clientId).get();
        Establishment establishment = establishmentDao.findById(establishmentId).get();
        review.setClient(client);
        review.setEstablishment(establishment);
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setAverageCheck(reviewDto.getAverageCheck());
        reviewDao.save(review);
    }

//    public Review getById(int id) {
//        return reviewDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Review not found with id " + id));
//    }
//    public Review getById(int id) {
//        Review review = reviewDao.findById(id);
//        if (review == null) {
//            throw new EntityNotFoundException("User not found with id " + id);
//        }
//        return review;
//    }
//
//    public List<Review> getByUserId(int userId) {
//        return reviewDao.findByUserId(userId);
//    }
//
//    public List<Review> getByEstablishmentId(int establishmentId) {
//        return reviewDao.findByEstablishmentId(establishmentId);
//    }
//
//    public Review update(int id, ReviewDto reviewDto) {
//        Review review = getById(id);
//        review.setRating(reviewDto.getRating());
//        review.setComments(reviewDto.getComment());
//        review.setAverageCheck(reviewDto.getAverageCheck());
//        return reviewDao.save(review);
//    }
//
    public void delete(int id) {
        Review review = reviewDao.findById(id).get();
        reviewDao.delete(review);
    }
}
