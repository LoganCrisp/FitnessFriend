package com.fitnessfriend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessfriend.dto.UserDto;
import com.fitnessfriend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// Registration endpoint
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto, @RequestParam String password) {
		UserDto registeredUser = userService.registerUser(userDto, password);
		return ResponseEntity.ok(registeredUser);
	}

	// Login endpoint
	@PostMapping("/login")
	public ResponseEntity<UserDto> loginUser(@RequestParam String username, @RequestParam String password) {
		UserDto loggedInUser = userService.loginUser(username, password);
		return ResponseEntity.ok(loggedInUser);
	}
}
