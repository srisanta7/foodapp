package com.foodApp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.foodApp.controller.AdminController;
import com.foodApp.dao.AdminRepo;
import com.foodApp.model.User;

@Service
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	private AdminRepo ad;
	
	@Override
	public User saveUser(User user) {
		User resultUser = new User();
		try {
			logger.info("Inside saveUser method with user : "+user);
			resultUser = ad.save(user);
			logger.info("successfully inserted user in database with user : "+resultUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultUser;
	}

	public AdminServiceImpl(AdminRepo ad) {
		super();
		this.ad = ad;
	}

	@Override
	public User logIn(User user) {
		User responseUser = new User();
		try {
			logger.info("Inside login method with user : "+user);
			List<User> users = ad.findAll();
			for(User u : users) {
				if(u.getEmail().equals(user.getEmail())&&u.getPassword().equals(user.getPassword())) {
					responseUser=u;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseUser;
	}

	@Override
	public User getUser(int id) {
		Optional<User> optional = ad.findById(id);
		User user = optional.orElse(null);
		return user;
	}
    
}
