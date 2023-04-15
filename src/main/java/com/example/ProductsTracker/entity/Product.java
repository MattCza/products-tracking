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
//    @Column(name = "price")
//    private double price;
//    @Column(name = "discount")
//    private boolean discount;

}
