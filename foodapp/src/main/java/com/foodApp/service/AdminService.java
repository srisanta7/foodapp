package com.foodApp.service;

import com.foodApp.model.User;

public interface AdminService {
           public User saveUser(User user);
           public User logIn(User user);
           public User getUser(int id);
}
