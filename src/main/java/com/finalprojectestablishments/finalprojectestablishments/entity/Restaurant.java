package com.finalprojectestablishments.finalprojectestablishments.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    @NotEmpty
    private String restaurantName;
//    @NotBlank
    @Column(nullable = true)
    private String address;

//    @NotBlank
@Column(nullable = true)
    private String schedule;

//    @NotBlank
@Column(nullable = true)
    private String contacts;

//    @NotBlank
@Column(nullable = true)
    private String news;

//    @NotBlank
@Column(nullable = true)
    private String type;

    //    @NotBlank
    @Column(nullable = true)
    private Double averageCheck;

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnoreProperties("restaurant")
    private List<Review> reviews;

}
