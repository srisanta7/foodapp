package com.foodApp.service;

import com.foodApp.model.Cart;

public interface CartService {
	
      public Cart save(Cart cart);
      public void deleteCart(int userId);
      public Cart getCart(int cartId);
}
