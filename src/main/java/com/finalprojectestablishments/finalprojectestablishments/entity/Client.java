package com.finalprojectestablishments.finalprojectestablishments.entity;//package com.example.test_march.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String clientName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

}