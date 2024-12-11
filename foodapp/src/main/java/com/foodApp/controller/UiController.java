package com.foodApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.foodApp.model.Cart;
import com.foodApp.model.CartItem;
import com.foodApp.model.Order;
import com.foodApp.model.Product;
import com.foodApp.model.User;
import com.foodApp.model.UserMessage;
import com.foodApp.service.AdminServiceImpl;
import com.foodApp.service.CartServiceImpl;
import com.foodApp.service.OrderServiceImpl;
import com.foodApp.service.ProductServiceImpl;
import com.foodApp.service.UserMessageServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/foodapp")
public class UiController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class); 
	private static ProductServiceImpl ps;
	private static AdminServiceImpl as;
	private static CartServiceImpl cs;
	private static OrderServiceImpl os;
	private static UserMessageServiceImpl us;

	public UiController(ProductServiceImpl ps, AdminServiceImpl as,CartServiceImpl cs,OrderServiceImpl os,UserMessageServiceImpl us) {
		this.ps = ps;
		this.as = as;
		this.cs = cs;
		this.os = os;
		this.us = us;
	}

	@GetMapping("/home")
	public static ModelAndView home(@RequestParam("id") int id, ModelAndView model) {
		User user = as.getUser(id);
		logger.info("Inside home controller with user : " + user);
		model.addObject("user", user);
		logger.info("Inside home controller");
		model.setViewName("home");
		return model;
	}

	@GetMapping("/contactus")
	public static ModelAndView about(@RequestParam("id") int id, ModelAndView model) {
		logger.info("Inside about controller");
		User user = as.getUser(id);
		logger.info("Inside about controller with user : " + user);
		model.addObject("user", user);
		model.setViewName("contactUs");
		return model;
	}

	@GetMapping("/menu")
	public static ModelAndView menu(@RequestParam("id") int id, ModelAndView model) {
		logger.info("Inside menu controller");
		List<Product> products = ps.getMenu();
		logger.info("after getMenu with products : " + products);
		model.addObject("products", products);
		User user = as.getUser(id);
		logger.info("Inside menu controller with user : " + user);
		model.addObject("user", user);
		model.setViewName("menu");
		return model;
	}

	@GetMapping("/profile")
	public static ModelAndView profile(@RequestParam("id") int id, ModelAndView model) {
		logger.info("Inside profile controller with id : " + id);
		User user = as.getUser(id);
		logger.info("Inside profile controller with user : " + user);
		model.setViewName("profile");
		model.addObject("user", user);
		return model;
	}

	@GetMapping("/additem")
	public static ModelAndView addItem(@RequestParam("uid") int id, ModelAndView model) {
		logger.info("Inside addItem controller");
		model.setViewName("addItem");
		model.addObject(new Product());
		User user = as.getUser(id);
		logger.info("Inside orders controller with user : " + user);
		model.addObject("user", user);
		return model;
	}

	@GetMapping("/editmenu")
	public static ModelAndView editMenu(@RequestParam("uid") int id, ModelAndView model) {
		logger.info("Inside editMenu controller");
		model.setViewName("editMenu");
		List<Product> products = ps.getMenu();
		logger.info("after getMenu with products : " + products);
		model.addObject("products", products);
		User user = as.getUser(id);
		logger.info("Inside orders controller with user : " + user);
		model.addObject("user", user);
		return model;
	}

	@PostMapping("/submitcart")
	public ModelAndView addToCart(
	        @RequestParam Map<String, String> cartItems,
	        @RequestParam("userId") Long userId,
	        HttpSession session) {

	    // Log the request for debugging purposes
	    logger.info("Inside submitcart with cartItems: {} and userId: {}", cartItems, userId);

	    // Create a Cart object and set its userId
	    Cart cart = new Cart();
	    cart.setUserId(Integer.parseInt(userId.toString()));
	    List<CartItem> cartItemList = new ArrayList<>();

	    // Iterate over the cart items and add them to the list
	    for (Map.Entry<String, String> entry : cartItems.entrySet()) {
	        try {
	            // Process only cart items with valid quantities
	            if (entry.getKey().startsWith("cartItems[")) {
	                int productId = Integer.parseInt(entry.getKey().replaceAll("[^0-9]", ""));
	                String quantityStr = entry.getValue();
	                int quantity = 0;

	                if (quantityStr != null && !quantityStr.isEmpty()) {
	                    try {
	                        quantity = Integer.parseInt(quantityStr);
	                    } catch (NumberFormatException e) {
	                        logger.warn("Invalid quantity for productId {}: {}", productId, quantityStr);
	                    }
	                }

	                // Only add items with valid quantity
	                if (quantity > 0) {
	                    CartItem cartItem = new CartItem();
	                    cartItem.setProductId(productId);
	                    cartItem.setQuantity(quantity);
	                    cartItem.setCart(cart);  // Associate CartItem with Cart
	                    cartItemList.add(cartItem);
	                }
	            }
	        } catch (NumberFormatException e) {
	            logger.warn("Invalid productId format: {}", entry.getKey(), e);
	        }
	    }

	    // Set the cart items and save the cart
	    cart.setCartItems(cartItemList);  // Associate the list with the Cart object
	    
	    cs.deleteCart(cart.getUserId());
	    Cart calculatedCart = ps.calculateCart(cart);
	    Cart savedCart = cs.save(calculatedCart);  // This will save the Cart and cascade to save the CartItems
	    logger.info("Cart items added successfully");
	    logger.info("Cart : "+savedCart);
	    ModelAndView model = new ModelAndView();
	    model.addObject("cart",savedCart);
	    User user = as.getUser(Integer.parseInt(userId.toString()));
		logger.info("Inside orders controller with user : " + user);
		model.addObject("user", user);
	    model.setViewName("viewCart");
	    return model;
	}

	@PostMapping("/createorder")
	public ModelAndView createOrder(@RequestParam("cartId") int cartId) {
        logger.info("cartid"+cartId);
        Cart cart = cs.getCart(cartId);
        User user = as.getUser(cart.getUserId());
		logger.info("Inside orders controller with user : " + user);
		os.saveOrder(cart);
		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
        model.setViewName("ordersucess");
        return model;
	}
	
	@GetMapping("/orders")
	public ModelAndView showOrderList(@RequestParam("id") int id) {
        logger.info("uderid"+id);
        User user = as.getUser(id);
		logger.info("Inside orders controller with user : " + user);
		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
		List<Order>orderList = os.getOrderList(id);
		 logger.info("orderList : "+orderList);
		model.addObject(orderList);
        model.setViewName("orders");
        return model;
	}
	
	@GetMapping("/logout")
	public ModelAndView logOut(@RequestParam("uid") int id) {
		ModelAndView model = new ModelAndView();
        model.setViewName("login");
        model.addObject(new User());
        return model;
	}
	
	@PostMapping("/message")
	public ModelAndView doMessage(@RequestParam("id") int id,
		                 	      @RequestParam("name") String name,
		                	      @RequestParam("email") String email, 
		                	      @RequestParam("message") String message) {
		
		UserMessage userMessage = new UserMessage();
		userMessage.setName(name);
		userMessage.setEmail(email);
		userMessage.setMessage(message);
		logger.info("Inside domessage controller with message : " + userMessage);
		us.saveMessage(userMessage);
		ModelAndView model = new ModelAndView();
        User user = as.getUser(id);
		logger.info("Inside domessage controller with user : " + user);
		model.addObject("user", user);
		model.setViewName("contactUs");
        return model;
	}
	
	@GetMapping("/getmessages")
	public ModelAndView showMessages(@RequestParam("id") int id) {
		
		List<UserMessage> userMessages = new ArrayList<UserMessage>();
		
		userMessages = us.showAllMessage();
		
		ModelAndView model = new ModelAndView();
        User user = as.getUser(id);
		logger.info("Inside domessage controller with user : " + user);
		model.addObject("user", user);
		model.addObject("userMessage",userMessages);
		model.setViewName("showMessages");
        return model;
	}
	
	@GetMapping("/deleteMessage")
	public ModelAndView deleteMessage(@RequestParam("userId") int id,@RequestParam("messageId") int mid) {
		List<UserMessage> userMessages = new ArrayList<UserMessage>();
		us.deleteMessage(mid);
		ModelAndView model = new ModelAndView();
        User user = as.getUser(id);
        userMessages = us.showAllMessage();
		logger.info("Inside domessage controller with user : " + user);
		model.addObject("user", user);
		model.addObject("userMessage",userMessages);
		model.setViewName("showMessages");
        return model;
	}
	
}
