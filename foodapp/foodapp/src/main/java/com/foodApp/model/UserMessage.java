package com.foodApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String name;
    private String email;
    private String message;
	@Override
	public String toString() {
		return "UserMessage [id=" + id + ", name=" + name + ", email=" + email + ", message=" + message + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserMessage(int id, String name, String email, String message) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.message = message;
	}
}
