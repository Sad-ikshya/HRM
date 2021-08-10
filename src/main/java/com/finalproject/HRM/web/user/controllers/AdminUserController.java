package com.finalproject.HRM.web.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.finalproject.HRM.web.user.dtos.UserPaginationData;
import com.finalproject.HRM.web.user.dtos.FileUpload;
import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.service.UserService;

@RestController
@RequestMapping("/admins/{adminid}/users")
public class AdminUserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public UserPaginationData getUserList(
						@PathVariable String adminid,
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
	public UserDto updateUser(
						@PathVariable String adminid,
						@PathVariable String id,
						@RequestBody UserDto user
						)
	{
		return userService.updateUser(id, user,adminid);
	}
	
//	@PostMapping("/{id}/image")
	/*public FileUpload uploadImage(
						@PathVariable String adminid,
						@PathVariable String id,
						@RequestParam("image") MultipartFile image
						) throws Exception
	{
		return userService.uploadImage(image);
	}*/
	
	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable String adminid,@PathVariable String id)
	{
		return userService.getUserById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String adminid,@PathVariable String id)
	{
		userService.deleteUserById(id);
	}
}
