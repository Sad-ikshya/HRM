package com.finalproject.HRM.web.user.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.web.user.entities.User;

public interface UserRepository extends MongoRepository<User, String>{

	public Optional<User> getByEmail(String email);
	public Page<User> findByDepartment(String department,Pageable page);
	public Page<User> findByDesignation(String designation,Pageable page);
	public Page<User> findByDepartmentAndDesignation(String department,String designation,Pageable page);
}
