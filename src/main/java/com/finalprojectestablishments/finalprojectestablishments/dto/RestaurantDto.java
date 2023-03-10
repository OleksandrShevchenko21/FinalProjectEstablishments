package com.finalprojectestablishments.finalprojectestablishments.dto;

import com.finalprojectestablishments.finalprojectestablishments.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RestaurantDto {
    private int id;
    private String restaurantName;

    private String type;

    private String address;

    private String schedule;

    private String contacts;

    //    private Double averageRating;
    private Double averageCheck;
    //
    private List<Review> reviews;

    // constructors, getters, and setters

}
