package com.foodApp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodApp.controller.AdminController;
import com.foodApp.dao.OrderItemRepo;
import com.foodApp.dao.OrderRepo;
import com.foodApp.model.Cart;
import com.foodApp.model.CartItem;
import com.foodApp.model.Order;
import com.foodApp.model.OrderItem;

@Service
public class OrderServiceImpl implements OrderService{
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private OrderRepo or;
	@Autowired
	private OrderItemRepo oir;
	
	
	public OrderServiceImpl(OrderRepo or,OrderItemRepo oir) {
		super();
		
		this.or = or;
		this.oir = oir;
	}


	@Override
	public void saveOrder(Cart cart) {
	    Order order = new Order();
	    List<OrderItem> orderItems = new ArrayList<>();

	    for (CartItem cartItem : cart.getCartItems()) {
	        OrderItem orderItem = new OrderItem();
	        orderItem.setProductId(cartItem.getProductId());
	        orderItem.setProductName(cartItem.getProductName());
	        orderItem.setQuantity(cartItem.getQuantity());
	        orderItem.setPrice(cartItem.getPrice());

	        // Set the parent order for the order item
	        orderItem.setOrder(order);
        
	        orderItems.add(orderItem);
	    }

	    // Set order properties
	    order.setSubtotal(cart.getSubtotal());
	    order.setTax(cart.getTax());
	    order.setTotal(cart.getTotal());
	    order.setUserId(cart.getUserId());
	    order.setDate(LocalDateTime.now());
	    order.setOrderItems(orderItems);

	    // Save the order, cascading will save order items
	    or.save(order);
	    logger.info("Order added successfully");
	}



	@Override
	public List<Order> getOrderList(int id) {
		List<Order> result = new ArrayList<Order>();
		List<Order> orderList= or.findAll();
		for(Order order : orderList) {
			if(order.getUserId()==id) {
				result.add(order);
			}
		}
		return result;
	}
}
