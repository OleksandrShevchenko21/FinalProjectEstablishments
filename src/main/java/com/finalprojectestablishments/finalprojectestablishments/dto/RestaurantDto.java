package com.finalprojectestablishments.finalprojectestablishments.dto;

import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    private Double averageCheck;

    private LocalDate dateOfPublish;
    public RestaurantDto(RestaurantDto restaurantDto) {

    }
}
