package com.foodApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodApp.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    
}
