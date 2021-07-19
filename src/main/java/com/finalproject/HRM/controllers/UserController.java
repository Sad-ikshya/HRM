package com.finalproject.HRM.controllers;

import java.util.List;

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

import com.finalproject.HRM.dtos.UserDto;
import com.finalproject.HRM.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public UserDto addUser(@RequestBody UserDto user)
	{
		return userService.saveUser(user);
	}
	
	@GetMapping
	public List<UserDto> getUserList(
						@RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
						@RequestParam(name = "limit", defaultValue = "2") int limit,
						@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
						@RequestParam(name = "department", required = false) String department,
						@RequestParam(name = "designation", required = false) String designation
			)
	{
		return userService.getAllUser(pageNo,limit,sortBy,department, designation);
	}
	
	@PutMapping("/{id}")
	public UserDto updateUser(@PathVariable String id,@RequestBody UserDto user)
	{
		return userService.updateUser(id, user);
	}
	
	@PostMapping("/uploadimage")
	public String uploadImage(@RequestParam("image") MultipartFile image) throws Exception
	{
		return userService.uploadImage(image);
	}
	
	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable String id)
	{
		return userService.getUserById(id);
	}
}
