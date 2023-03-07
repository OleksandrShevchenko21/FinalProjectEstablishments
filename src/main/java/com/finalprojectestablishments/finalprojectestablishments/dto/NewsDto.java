package com.finalprojectestablishments.finalprojectestablishments.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsDto {

    private int id;


    private String generalNews;


    private String promotionNews;


    private String eventNews;


    private int restaurantId;
}
