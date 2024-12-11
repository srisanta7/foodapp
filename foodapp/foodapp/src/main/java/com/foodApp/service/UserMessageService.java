package com.foodApp.service;

import java.util.List;

import com.foodApp.model.UserMessage;

public interface UserMessageService {
       public void saveMessage(UserMessage userMessage);
       public void deleteMessage(int id);
       public List<UserMessage> showAllMessage();
}
