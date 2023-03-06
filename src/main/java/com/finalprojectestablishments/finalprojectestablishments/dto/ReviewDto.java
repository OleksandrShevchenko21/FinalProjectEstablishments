package com.finalprojectestablishments.finalprojectestablishments.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String comment;
//    @NotNull
//    @DecimalMax(5.0)
//    @DecimalMin(1.0)
    private int rating;
//    @NotNull
    private int userId;
//    @NotNull
    private int restaurantId;
//    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal averageCheck;
}