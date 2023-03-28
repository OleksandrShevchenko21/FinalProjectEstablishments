package com.finalprojectestablishments.finalprojectestablishments.entity.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promotion_news")
public class PromotionNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "promotion_news")
    private String promotionNews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    private Restaurant restaurant;
}
