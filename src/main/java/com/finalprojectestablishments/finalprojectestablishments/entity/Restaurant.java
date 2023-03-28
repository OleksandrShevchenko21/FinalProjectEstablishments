package com.finalprojectestablishments.finalprojectestablishments.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finalprojectestablishments.finalprojectestablishments.entity.news.EventNews;
import com.finalprojectestablishments.finalprojectestablishments.entity.news.GeneralNews;
import com.finalprojectestablishments.finalprojectestablishments.entity.news.PromotionNews;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String restaurantName;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String schedule;

    @Column(nullable = true)
    private String contacts;

    @Column(nullable = true)
    private String news;

    @Column(nullable = true)
    private String type;

    @Column(nullable = true)
    private Double averageCheck;

    @Column(nullable = true)
    private LocalDate dateOfPublish;

    @Column(nullable = true)
    private Double averageRating;


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("restaurant")
    private List<Review> reviews;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("restaurant")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("restaurant")
    private List<EventNews> eventNews;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("restaurant")
    private List<GeneralNews> generalNews;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("restaurant")
    private List<PromotionNews> promotionNews;

    @ManyToMany
    @JoinTable(
            name = "favorites_restaurants",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

}
