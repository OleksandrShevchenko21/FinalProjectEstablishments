package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dao.ReviewDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.EstablishmentDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.ReviewDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Establishment;
import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import com.finalprojectestablishments.finalprojectestablishments.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewDao reviewDao;
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> all = reviewDao.findAll();
        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getOneReview(@PathVariable int id) {
        Review review = reviewDao.findById(id).get();
        return new ResponseEntity<>(review, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/{clientId}/{establishmentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReview(@PathVariable int clientId,
                           @PathVariable int establishmentId,
                           @RequestBody ReviewDto reviewDto) {
        reviewService.save(clientId,establishmentId,reviewDto);
    }

    @PatchMapping("/{id}")
    public void updateReview(@PathVariable int id, @RequestBody ReviewDto reviewDto) {
        Review review = reviewDao.findById(id).get();
        review.setRating(reviewDto.getRating());
//    todo clientID
//    todo establishmentID
        reviewDao.save(review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable int id) {

        reviewService.delete(id);
    }

//    @GetMapping("/{id}")
//    public Review getById(@PathVariable int id) {
//        return reviewService.getById(id);
//    }
//
//    @GetMapping(params = "establishmentId")
//    public List<Review> getByRestaurantId(@RequestParam int establishmentId) {
//        return reviewService.getByEstablishmentId(establishmentId);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable int id) {
//        reviewDao.deleteById(id);
//    }
}
