package com.example.ProductsTracker.product;

import com.example.ProductsTracker.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
