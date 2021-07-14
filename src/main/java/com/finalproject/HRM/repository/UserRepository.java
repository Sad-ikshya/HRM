package com.finalproject.HRM.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

	public User findByEmail(String email);
}
