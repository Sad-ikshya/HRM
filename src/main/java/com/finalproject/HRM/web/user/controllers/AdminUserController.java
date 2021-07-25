package com.finalproject.HRM.web.user.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.service.UserService;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

	@Autowired
	UserService userService;
	
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
}
