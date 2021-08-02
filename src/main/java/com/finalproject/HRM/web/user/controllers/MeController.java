package com.finalproject.HRM.web.user.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.service.UserService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/me")
public class MeController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public UserDto getLogedInUserDetail(@ApiIgnore Principal user)
	{
		System.out.println(user.getName());
		return userService.getUserByEmail(user.getName());
	}
}
