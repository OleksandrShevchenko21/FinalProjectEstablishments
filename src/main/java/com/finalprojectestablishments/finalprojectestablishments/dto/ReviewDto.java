package com.finalprojectestablishments.finalprojectestablishments.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private int id;

    private String comment;

    private int rating;

    private String userName;

    private int restaurantId;

    private Double averageCheck;
}
