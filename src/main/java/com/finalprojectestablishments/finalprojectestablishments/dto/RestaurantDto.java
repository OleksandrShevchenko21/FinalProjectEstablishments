package com.finalprojectestablishments.finalprojectestablishments.dto;


import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


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

    private Double averageCheck;

    private LocalDate dateOfPublish;

    private Double averageRating;

//    private String userName;
    public RestaurantDto(RestaurantDto restaurantDto) {

    }
}
