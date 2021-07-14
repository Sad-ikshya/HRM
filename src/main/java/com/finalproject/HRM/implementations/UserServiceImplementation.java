package com.finalproject.HRM.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.dtos.UserDto;
import com.finalproject.HRM.entities.User;
import com.finalproject.HRM.repositories.UserRepository;
import com.finalproject.HRM.services.UserService;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserDto> getAllUser() {
		List<User> user= userRepository.findAll();
		List<UserDto> userDtoList=new ArrayList<>();
		for(User u: user) {
			UserDto userDto=UserDto.builder().id(u.getId())
							.firstname(u.getFirstname())
							.lastname(u.getLastname())
							.email(u.getEmail())
							.department(u.getDepartment())
							.designation(u.getDesignation())
							.bio(u.getBio())
							.joinedDate(u.getJoinedDate())
							.role(u.getRole())
							.photo(u.getPhoto())
							.provider(u.getProvider()).build();
			
			userDtoList.add(userDto);
		}
		return userDtoList;
	}

	@Override
	public UserDto saveUser(UserDto user) {
		User userEntity=User.builder().id(user.getId())
				.firstname(user.getFirstname())
				.lastname(user.getLastname())
				.email(user.getEmail())
				.department(user.getDepartment())
				.designation(user.getDesignation())
				.bio(user.getBio())
				.joinedDate(user.getJoinedDate())
				.role(user.getRole())
				.photo(user.getPhoto())
				.provider(user.getProvider()).build();
		
		userEntity=userRepository.save(userEntity);
		user.setId(userEntity.getId());
		return user;
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user=userRepository.getByEmail(email);
		return UserDto.builder().id(user.getId())
				.firstname(user.getFirstname())
				.lastname(user.getLastname())
				.email(user.getEmail())
				.department(user.getDepartment())
				.designation(user.getDesignation())
				.bio(user.getBio())
				.joinedDate(user.getJoinedDate())
				.role(user.getRole())
				.photo(user.getPhoto())
				.provider(user.getProvider()).build();
	}

	@Override
	public UserDto updateUser(String id, UserDto user) {
		User userEntity=User.builder().id(id)
				.firstname(user.getFirstname())
				.lastname(user.getLastname())
				.email(user.getEmail())
				.department(user.getDepartment())
				.designation(user.getDesignation())
				.bio(user.getBio())
				.joinedDate(user.getJoinedDate())
				.role(user.getRole())
				.photo(user.getPhoto())
				.provider(user.getProvider()).build();
		
		userEntity=userRepository.save(userEntity);
		user.setId(userEntity.getId());
		return user;
	}

}