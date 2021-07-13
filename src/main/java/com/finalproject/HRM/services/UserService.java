package com.finalproject.HRM.services;

import java.util.List;

import com.finalproject.HRM.dtos.UserDto;

public interface UserService {
	public List<UserDto> getAllUser();
	public UserDto saveUser(UserDto user);
	public UserDto getUserByEmail(String email);
	public UserDto updateUser(String id, UserDto user);

}
