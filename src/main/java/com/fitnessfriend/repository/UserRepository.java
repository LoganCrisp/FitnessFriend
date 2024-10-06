package com.fitnessfriend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitnessfriend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
