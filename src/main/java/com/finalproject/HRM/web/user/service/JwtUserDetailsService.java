package com.finalproject.HRM.web.user.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.web.user.repositories.UserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<com.finalproject.HRM.web.user.entities.User> user = userRepository.getByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with name:" + email);
		}
		return new User(user.get().getEmail(), user.get().getId(), new ArrayList<>());
	}
}
