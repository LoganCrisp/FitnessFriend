package com.fitnessfriend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitnessfriend.entity.User;
import com.fitnessfriend.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Sets up the test case by clearing user-related data.
	 */
	@BeforeEach
	public void setUp() {
		userRepository.deleteAll();
	}

	/**
	 * Test user registration using POST /auth/register.
	 */
	@Test
	@Transactional
	public void testRegisterUser() throws Exception {
		// Create user DTO
		User user = new User();
		user.setUsername("testuser");
		user.setPassword("password123");
		user.setEmail("testuser@example.com");

		// Convert user to JSON string
		String userJson = objectMapper.writeValueAsString(user);

		// Perform the registration request
		mvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value("User registered successfully"))
				.andDo(print());
	}

	/**
	 * Test successful login using POST /auth/login.
	 */
	@Test
	@Transactional
	public void testLoginUser_Success() throws Exception {
		// Step 1: Register a user in the database
		User user = new User();
		user.setUsername("testuser");
		user.setPassword(passwordEncoder.encode("password123"));
		user.setEmail("testuser@example.com");
		userRepository.save(user);

		// Step 2: Create the login payload
		User loginRequest = new User();
		loginRequest.setUsername("testuser");
		loginRequest.setPassword("password123");

		String loginRequestJson = objectMapper.writeValueAsString(loginRequest);

		// Step 3: Perform the login request
		mvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(loginRequestJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value("Login successful!")).andDo(print());
	}

	/**
	 * Test login failure (wrong password).
	 */
	@Test
	@Transactional
	public void testLoginUser_Failure_WrongPassword() throws Exception {
		// Step 1: Register a user in the database
		User user = new User();
		user.setUsername("testuser");
		user.setPassword(passwordEncoder.encode("password123"));
		user.setEmail("testuser@example.com");
		userRepository.save(user);

		// Step 2: Create the login payload with the wrong password
		User loginRequest = new User();
		loginRequest.setUsername("testuser");
		loginRequest.setPassword("wrongpassword");

		String loginRequestJson = objectMapper.writeValueAsString(loginRequest);

		// Step 3: Perform the login request with wrong password
		mvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(loginRequestJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value("Invalid username or password"))
				.andDo(print());
	}

	/**
	 * Test login failure (user not found).
	 */
	@Test
	@Transactional
	public void testLoginUser_Failure_UserNotFound() throws Exception {
		// Step 1: Create the login payload for a non-existing user
		User loginRequest = new User();
		loginRequest.setUsername("nonexistentuser");
		loginRequest.setPassword("password123");

		String loginRequestJson = objectMapper.writeValueAsString(loginRequest);

		// Step 2: Perform the login request with a non-existent user
		mvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(loginRequestJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value("Invalid username or password"))
				.andDo(print());
	}
}
