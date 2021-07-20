package com.finalproject.HRM.web.user.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.finalproject.HRM.web.user.dtos.UserDto;

public interface UserService {

	public List<UserDto> getAllUser(int pageNo, int limit, String sortBy,String department, String designation);
	public UserDto saveUser(UserDto user);
	public UserDto getUserByEmail(String email);
	public UserDto updateUser(String id, UserDto user);
	public String uploadImage(MultipartFile image) throws Exception;
	public UserDto getUserById(String id);
	public String deleteUserById(String id);
}
