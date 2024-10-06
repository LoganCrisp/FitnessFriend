package com.fitnessfriend.mapper;

import com.fitnessfriend.dto.UserDto;
import com.fitnessfriend.entity.User;

public class UserMapper {

	public static UserDto toDto(User user) {
		return new UserDto(user.getId(), user.getUsername(), user.getEmail());
	}

	public static User toEntity(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		return user;
	}
}
