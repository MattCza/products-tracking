package com.example.ProductsTracker.Service;

import com.example.ProductsTracker.Repository.ProductRepository;
import com.example.ProductsTracker.Repository.UserRepository;
import com.example.ProductsTracker.entity.Product;
import com.example.ProductsTracker.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProductService {

    private ProductRepository productRepository;
    private UserRepository userRepository;

    public ProductService (ProductRepository productRepository, UserRepository userRepository){
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/products")
    public ResponseEntity addProduct(@RequestHeader("email") String email, @RequestBody String name){
        Optional<User> userFromDb = userRepository.findByEmail(email);

        if (userFromDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Product product = new Product(userFromDb.get(), name);
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }



}