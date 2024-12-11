package com.foodApp.service;

import java.util.List;

import com.foodApp.model.Cart;
import com.foodApp.model.Product;

public interface ProductService {
	public boolean addProduct(Product product);
	public List<Product> getMenu();
	public boolean updateProduct(Product product);
	public boolean deleteItem(int id);
	public Product getProduct(int id);
	public Cart calculateCart(Cart cart);
}
