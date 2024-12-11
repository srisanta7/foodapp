package com.foodApp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cart {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int cartId;
   private int userId;
   @OneToMany(cascade = CascadeType.ALL)  // Ensure cascading is enabled
   private List<CartItem> cartItems;
   private double subtotal;
   private double tax;
   private double total;
public Cart(int cartId, int userId, List<CartItem> cartItems, double subtotal, double tax, double total) {
	super();
	this.cartId = cartId;
	this.userId = userId;
	this.cartItems = cartItems;
	this.subtotal = subtotal;
	this.tax = tax;
	this.total = total;
}
public Cart() {
	super();
	// TODO Auto-generated constructor stub
}
public int getCartId() {
	return cartId;
}
public void setCartId(int cartId) {
	this.cartId = cartId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public List<CartItem> getCartItems() {
	return cartItems;
}
public void setCartItems(List<CartItem> cartItems) {
	this.cartItems = cartItems;
}
public double getSubtotal() {
	return subtotal;
}
public void setSubtotal(double subtotal) {
	this.subtotal = subtotal;
}
public double getTax() {
	return tax;
}
public void setTax(double tax) {
	this.tax = tax;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
@Override
public String toString() {
	return "Cart [cartId=" + cartId + ", userId=" + userId + ", cartItems=" + cartItems + ", subtotal=" + subtotal
			+ ", tax=" + tax + ", total=" + total + "]";
}

   
}
