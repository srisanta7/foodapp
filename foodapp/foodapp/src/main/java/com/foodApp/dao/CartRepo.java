package com.foodApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodApp.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    // You can define custom queries here if needed
}
