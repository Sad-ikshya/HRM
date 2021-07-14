package com.finalproject.HRM.service;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.finalproject.HRM.dto.UserDto;
import com.finalproject.HRM.entity.User;

public interface UserService {
	public UserDto addUser(UserDto user);
	public UserDto getUserByEmail(String email);
	public User signup(OAuth2User user);
}
