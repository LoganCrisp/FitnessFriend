package com.fitnessfriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitnessfriend.dto.UserDto;
import com.fitnessfriend.entity.User;
import com.fitnessfriend.exception.ResourceNotFoundException;
import com.fitnessfriend.mapper.UserMapper;
import com.fitnessfriend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserDto registerUser(UserDto userDto, String password) {
		User user = UserMapper.toEntity(userDto);
		user.setPassword(passwordEncoder.encode(password));
		User savedUser = userRepository.save(user);
		return UserMapper.toDto(savedUser);
	}

	public UserDto loginUser(String username, String password) {
		// Unwrap the Optional<User>
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		// Check if the password matches
		if (passwordEncoder.matches(password, user.getPassword())) {
			return UserMapper.toDto(user);
		} else {
			throw new RuntimeException("Invalid credentials");
		}
	}
}
