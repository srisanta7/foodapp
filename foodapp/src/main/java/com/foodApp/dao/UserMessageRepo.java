package com.foodApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodApp.model.UserMessage;

public interface UserMessageRepo extends JpaRepository<UserMessage, Integer>{

}
