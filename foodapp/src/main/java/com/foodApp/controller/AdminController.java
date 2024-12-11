package com.foodApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foodApp.model.User;
import com.foodApp.service.AdminServiceImpl;
//import com.foodApp.util.JwtUtil;

@RestController
@RequestMapping("/admin")
public class AdminController {
	 @Autowired
//	 private static JwtUtil jwtUtil=new JwtUtil();

	private static AdminServiceImpl adminServiceImpl;

    public AdminController(AdminServiceImpl adminServiceImpl) {
        this.adminServiceImpl = adminServiceImpl;
    }
    
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("/adminRegistration")
	public static ModelAndView adminRegistration(ModelAndView model) {
		logger.info("Inside adminRegistration controller");
		 model.addObject("user", new User());
		 model.setViewName("adminRegistration");
		return model;
	}
	
	@PostMapping("/saveUser")
	public static ModelAndView saveUser(@ModelAttribute User user, ModelAndView model) {
		logger.info("Inside saveUser controller with User : "+user);
		adminServiceImpl.saveUser(user);
		logger.info("successfully saved user : "+user);
		User responseUser = adminServiceImpl.logIn(user);
//		String token = jwtUtil.generateToken(user.getEmail());
//		logger.info("after login method : "+f);
//		logger.info("after login method with jwt token : "+token);
		if(!responseUser.getEmail().isEmpty()) { model.setViewName("home");return model;}
        else { model.setViewName("login");return model;}
	}

	@GetMapping("/login")
	public static ModelAndView login(ModelAndView model) {
		logger.info("Inside login controller");

		model.addObject("user", new User());
		model.setViewName("login");
		return model;
	}	
	
	@PostMapping("/validate")
	public static ModelAndView validate(@ModelAttribute User user,ModelAndView model) {
		
		logger.info("validating user : "+user);
		User responseUser = adminServiceImpl.logIn(user);
//		String token = jwtUtil.generateToken(user.getEmail());
//		model.addObject("token", token);
//		logger.info("after login method : "+f);
//		logger.info("after login method with jwt token : "+token);
		logger.info("user : "+responseUser);
		if(responseUser.getEmail()!=null && !responseUser.getEmail().isEmpty()) { 
			model.setViewName("home"); 
			model.addObject("user",responseUser); 
			return model;}
		else { 
			model.addObject("user", new User());
			model.setViewName("login");
			return model;
			}
	}
}
