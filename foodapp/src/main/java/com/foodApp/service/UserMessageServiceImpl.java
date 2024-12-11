package com.foodApp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.foodApp.controller.AdminController;
import com.foodApp.dao.UserMessageRepo;
import com.foodApp.model.UserMessage;

@Service
public class UserMessageServiceImpl implements UserMessageService {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class); 

	private UserMessageRepo umr;
	
	public UserMessageServiceImpl(UserMessageRepo umr) {
		super();
		this.umr = umr;
	}

	@Override
	public void saveMessage(UserMessage userMessage) {
		umr.save(userMessage);
		logger.info("message saved successfully");
	}

	@Override
	public void deleteMessage(int id) {
		umr.deleteById(id);
	}

	@Override
	public List<UserMessage> showAllMessage() {
		List<UserMessage> userMessages = umr.findAll();
		return userMessages;
	}

}
