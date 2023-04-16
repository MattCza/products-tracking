package com.example.ProductsTracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @ManyToOne
    private User user;

    @NonNull
    @Column(name = "name")
    private String name;
    @NonNull
    @Column(name = "price")
    private Double price;
    @NonNull
    @Column(name = "discount")
    private Boolean discount;

}
