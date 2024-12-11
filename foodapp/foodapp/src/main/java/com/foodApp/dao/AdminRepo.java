package com.foodApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodApp.model.Product;
import com.foodApp.model.User;

@Repository
public interface AdminRepo extends JpaRepository<User, Integer> {
              
}
