package com.finalproject.HRM.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.services.UserService;
import com.finalproject.HRM.dtos.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return new ResponseEntity<List<UserDto>>(userService.getAllUser(), HttpStatus.OK);
	}
	@PostMapping("/")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user){
		return new ResponseEntity<UserDto>(userService.saveUser(user),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserDto user){
		return new ResponseEntity<UserDto>(userService.updateUser(id, user), HttpStatus.OK);
	}

	
}
