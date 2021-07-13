package com.finalproject.HRM.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.entities.User;

public interface UserRepository extends MongoRepository<User, String>{

	public User getByEmail(String email);
}
