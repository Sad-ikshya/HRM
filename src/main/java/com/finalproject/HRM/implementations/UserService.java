package com.finalproject.HRM.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.entities.User;
import com.finalproject.HRM.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	
	public User processOAuthPostLogin(String username) {
		User existUser= repo.getByEmail(username);
		
		if(existUser == null) {
			User newUser=new User();
			newUser.setEmail(username);
			repo.save(newUser);
		}
		return existUser;
	}
}
