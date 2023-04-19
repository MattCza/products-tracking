package com.example.ProductsTracker.Repository;

import com.example.ProductsTracker.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
