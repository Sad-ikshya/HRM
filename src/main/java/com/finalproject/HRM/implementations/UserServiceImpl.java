package com.finalproject.HRM.implementations;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.dto.UserDto;
import com.finalproject.HRM.entity.User;
import com.finalproject.HRM.repository.UserRepository;
import com.finalproject.HRM.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private Date today = new Date();
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User signup(OAuth2User user) {
		User userModel = userRepo.findByEmail(user.getAttribute("email"));
		if(userModel == null)
		{
			 userModel = User.builder().fullName(user.getAttribute("name"))
					.image(user.getAttribute("picture"))
					.email(user.getAttribute("email"))
					.role("employee")
					.joinedDate(today)
					.build();
			 userRepo.insert(userModel);
		}
		
		return userModel;
	}
	
	@Override
	public UserDto addUser(UserDto user) {
		if(userRepo.findByEmail(user.getEmail())==null)
		{
			User userEntity = User.builder().fullName(user.getFullName())
					.image(user.getImage())
					.email(user.getEmail())
					.role(user.getRole())
					.department(user.getDepartment())
					.designation(user.getDesignation())
					.bio(user.getBio())
					.joinedDate(today)
					.build();

			userRepo.insert(userEntity);
			return user;
		}
		
		return null;
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user = userRepo.findByEmail(email);
		if(user.equals(null))
		{
			return null;
		}
		UserDto userDto = UserDto.builder().fullName(user.getFullName())
											.image(user.getImage())
											.email(user.getEmail())
											.role(user.getRole())
											.department(user.getDepartment())
											.designation(user.getDesignation())
											.bio(user.getBio())
											.joinedDate(user.getJoinedDate())
											.build();
		return userDto;
	}

}
