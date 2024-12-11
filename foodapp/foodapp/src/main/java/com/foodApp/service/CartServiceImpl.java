package com.foodApp.service;

import com.foodApp.controller.AdminController;
import com.foodApp.dao.CartItemRepo;
import com.foodApp.dao.CartRepo;
import com.foodApp.model.Cart;
import com.foodApp.model.CartItem;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
	@Override
	public Cart save(Cart cart) {
		Cart savedCart = cartRepo.save(cart);
		logger.info("Inside save method after adding cart");
		return savedCart;
	}

	public CartServiceImpl(CartRepo cartRepo, CartItemRepo cartItemRepo) {
		super();
		this.cartRepo = cartRepo;
		this.cartItemRepo = cartItemRepo;
	}

	@Override
	public void deleteCart(int userId) {
		List<Cart> carts = cartRepo.findAll();
		for(Cart cart : carts) {
			if(cart.getUserId()==userId) {
				cartRepo.deleteById(cart.getCartId());
				logger.info("Inside deleteCart method after deleting cart");
				break;
			}
		}	
	}

	@Override
	public Cart getCart(int cartId) {
		Optional<Cart> optional = cartRepo.findById(cartId);
		Cart cart = optional.orElse(null);
		return cart;
	}
}

