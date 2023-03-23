//package com.finalprojectestablishments.finalprojectestablishments.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "favorites_restaurant")
//public class FavoritesRestaurant {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(nullable = true)
//    @NotEmpty
//    private String restaurantName;
//
//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", nullable = false)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "restaurant_id", nullable = false)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//
//    private Restaurant restaurant;
//}
