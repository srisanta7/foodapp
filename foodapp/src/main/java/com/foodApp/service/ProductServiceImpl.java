package com.foodApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.foodApp.controller.AdminController;
import com.foodApp.dao.AdminRepo;
import com.foodApp.dao.ProductRepo;
import com.foodApp.model.Cart;
import com.foodApp.model.CartItem;
import com.foodApp.model.Product;

@Service
public class ProductServiceImpl implements ProductService{

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
	private ProductRepo pd;
	
	public ProductServiceImpl(ProductRepo pd) {
		super();
		this.pd = pd;
	}

	@Override
	public boolean addProduct(Product product) {
		boolean f=false;
		pd.save(product);
		return f;
	}

	@Override
	public List<Product> getMenu() {
		List<Product> products = pd.findAll();
		return products;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean f = true;
		pd.save(product);
		return f;
	}

	@Override
	public boolean deleteItem(int id) {
		pd.deleteById(id);
		return true;
	}

	@Override
	public Product getProduct(int id) {
		Optional<Product> p = pd.findById(id);
		Product product = p.orElse(null);
		
		return product;
	}

	@Override
	public Cart calculateCart(Cart cart) {
		Cart calculatedCart = new Cart();
		double tax=0;
		double subtotal=0;
		double total=0;
		List<CartItem> cartItems = new ArrayList<CartItem>();
		calculatedCart.setCartId(cart.getCartId());
		calculatedCart.setUserId(cart.getUserId());
		for(CartItem cartItem: cart.getCartItems()) {
			Product product = getProduct(cartItem.getProductId());
			CartItem cr = new CartItem();
			cr.setCartItemId(cartItem.getCartItemId());
			cr.setProductId(cartItem.getProductId());
			cr.setProductName(product.getName());
			cr.setPrice(product.getPrice());
			cr.setQuantity(cartItem.getQuantity());
			subtotal = subtotal+(cr.getPrice()*cr.getQuantity());
			cartItems.add(cr);
		}
		tax = (subtotal*18)/100;
		total = subtotal+tax;
		calculatedCart.setCartItems(cartItems);
		calculatedCart.setSubtotal(subtotal);
        calculatedCart.setTax(tax);
        calculatedCart.setTotal(total);
		return calculatedCart;
	}
	
	
      
}
