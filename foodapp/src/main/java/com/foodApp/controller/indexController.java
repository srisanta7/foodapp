package com.foodApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class indexController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@GetMapping("/")
	public static ModelAndView index(ModelAndView model) {
		logger.info("Inside Index controller");
		model.setViewName("index");
		return model;
	}
}
