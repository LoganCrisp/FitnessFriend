package com.fitnessfriend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // Disable CSRF for testing (not recommended for production)
				.authorizeHttpRequests(authz -> authz.requestMatchers("/auth/register", "/auth/login").permitAll() // Allow
																													// public
																													// access
																													// to
																													// register
																													// and
																													// login
						.anyRequest().authenticated() // Protect all other routes
				).httpBasic(); // Use basic authentication for simplicity (JWT or session-based is better for
								// production)

		return http.build();
	}
}
