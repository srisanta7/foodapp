package com.foodApp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.foodApp.model.Product;
import com.foodApp.model.User;
import com.foodApp.service.AdminServiceImpl;
import com.foodApp.service.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
	private static ProductServiceImpl ps;
	private static AdminServiceImpl as;
	ProductController(ProductServiceImpl ps,AdminServiceImpl as){
		this.ps = ps;
		this.as = as;
	}


	@PostMapping("/addproducttodb")
	public static ModelAndView addProductToDB(@RequestParam("uid") int uid,@ModelAttribute Product product,ModelAndView model) {
		
		logger.info("inside addProductToDB with product : "+product);
		ps.addProduct(product);
		logger.info("sucessfully added product");
		List<Product> products = ps.getMenu();
		logger.info("after getMenu with products : "+products);
		model.addObject("products",products);
		model.setViewName("menu");
		User user = as.getUser(uid);
		logger.info("Inside updateProduct controller with user : "+user);
		model.addObject("user",user);
		return model;
	}
	
	@GetMapping("/edititem")
	public static ModelAndView updateProduct(@RequestParam("pid") int pid,@RequestParam("uid") int uid,ModelAndView model) {
		logger.info("in updateProduct Logging productId : "+pid);
		Product product = ps.getProduct(pid);
		logger.info("in updateProduct with product : "+product);
		model.addObject("product",product);
		model.setViewName("editItem");
		User user = as.getUser(uid);
		logger.info("Inside updateProduct controller with user : "+user);
		model.addObject("user",user);
		return model;
	}
	
	@PostMapping("/updateitemindb")
	public static ModelAndView updateItemInDb(@RequestParam("uid") int uid,Product product,ModelAndView model) {
		logger.info("in updateitemindb with product : "+product);
		ps.addProduct(product);
		logger.info("sucessfully updated product");
		model.setViewName("editMenu");
		List<Product> products = ps.getMenu();
		logger.info("after getMenu with products : "+products);
		model.addObject("products",products);
		User user = as.getUser(uid);
		logger.info("Inside orders controller with user : "+user);
		model.addObject("user",user);
		return model;
	}
	
	@GetMapping("/deleteitem")
	public static ModelAndView deleteProduct(@RequestParam("pid") int pid,@RequestParam("uid") int uid,ModelAndView model) {
		logger.info("Inside deleteProduct Logging productId : "+pid);
		ps.deleteItem(pid);
		logger.info("sucessfully deleted product");
		model.setViewName("editMenu");
		List<Product> products = ps.getMenu();
		logger.info("after getMenu with products : "+products);
		model.addObject("products",products);
		User user = as.getUser(uid);
		logger.info("Inside orders controller with user : "+user);
		model.addObject("user",user);
		return model;
	}
}
