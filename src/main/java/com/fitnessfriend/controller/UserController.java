package com.fitnessfriend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessfriend.entity.User;
import com.fitnessfriend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
}
