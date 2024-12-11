package com.foodApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.foodApp.model.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    @EntityGraph(attributePaths = "orderItems")
    List<Order> findByUserId(int userId);
}

