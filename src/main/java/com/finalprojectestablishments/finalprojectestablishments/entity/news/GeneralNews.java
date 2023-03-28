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
@Table(name = "general_news")
public class GeneralNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "general_news")
    private String generalNews;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    private Restaurant restaurant;
}
