package com.fitnessfriend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessfriend.entity.User;
import com.fitnessfriend.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		// Encrypt the password before saving the user
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "User registered successfully";
	}

	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {
		// Handle the Optional returned from findByUsername
		Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());

		if (optionalUser.isPresent()) {
			User dbUser = optionalUser.get();
			if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
				// Here, you'd return a JWT token or session
				return "Login successful!";
			}
		}

		return "Invalid username or password";
	}
}
