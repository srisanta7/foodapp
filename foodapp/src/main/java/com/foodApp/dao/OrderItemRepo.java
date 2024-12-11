package com.foodApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodApp.model.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer>{

}
