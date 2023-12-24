package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PrototypeWineryApplication {
	@Autowired
	private  UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PrototypeWineryApplication.class, args);
	}
	@PostConstruct
	public void init() {
		// Add a default user on startup
		//userService.register("admin", "admin", "admin", "admin@admin.com", Role.ROLE_ADMIN);
	}
}
