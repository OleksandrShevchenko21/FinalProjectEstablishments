package com.finalprojectestablishments.finalprojectestablishments.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {


    private int id;

    private String userName;

    private int restaurantId;

    private LocalDateTime reservationDateTime;

    private String purpose;

    private String gender;

    private int numPeople;

    private String whoPays;

    private double desiredExpenses;

}
