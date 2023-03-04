package com.finalprojectestablishments.finalprojectestablishments.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String comment;
//    @NotNull
//    @DecimalMax(5.0)
//    @DecimalMin(1.0)
    private int rating;
//    @NotNull
    private int clientId;
//    @NotNull
    private int establishmentId;
//    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal averageCheck;
}