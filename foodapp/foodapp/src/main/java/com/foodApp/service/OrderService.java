package com.foodApp.service;

import java.util.List;

import com.foodApp.model.Cart;
import com.foodApp.model.Order;

public interface OrderService {
      public void saveOrder(Cart cart);
      public List<Order> getOrderList(int id);
}
