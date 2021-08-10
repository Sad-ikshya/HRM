package com.finalproject.HRM.web.user.service;

import org.springframework.web.multipart.MultipartFile;

import com.finalproject.HRM.web.user.dtos.UserPaginationData;
import com.finalproject.HRM.web.user.dtos.FileUpload;
import com.finalproject.HRM.web.user.dtos.UserDto;

public interface UserService {

	public UserPaginationData getAllUser(int pageNo, int limit, String sortBy,String department, String designation);
	public UserDto getUserByEmail(String email);
	public UserDto updateUser(String id, UserDto user,String adminID);
	public FileUpload uploadImage(MultipartFile image) throws Exception;
	public UserDto getUserById(String id);
	public String deleteUserById(String id);
}
