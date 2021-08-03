package com.finalproject.HRM.web.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PutMapping("/{id}")
	public UserDto updateUser(@PathVariable String id,@RequestBody UserDto user)
	{
		return userService.updateUser(id, user,null);
	}
	
	@PostMapping("/{id}/image")
	public String uploadImage(@PathVariable String id,@RequestParam("image") MultipartFile image) throws Exception
	{
		return userService.uploadImage(image);
	}
	
	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable String id)
	{
		return userService.getUserById(id);
	}
}
