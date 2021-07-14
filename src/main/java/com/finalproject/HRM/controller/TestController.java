package com.finalproject.HRM.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.dto.UserDto;
import com.finalproject.HRM.entity.User;
import com.finalproject.HRM.service.UserService;

@RestController
public class TestController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String test()
	{
		return "access to all";
	}
	
	@RequestMapping("/restricted")
	public String restricted()
	{
		return "restrectid";
	}
	
	@RequestMapping("/signup")
	public User signup(@AuthenticationPrincipal OAuth2User principal)
	{
		return userService.signup(principal);
	}
}
