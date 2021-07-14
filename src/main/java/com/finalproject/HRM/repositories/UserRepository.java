package com.finalproject.HRM.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.entities.User;

public interface UserRepository extends MongoRepository<User, String>{

	public Optional<User> getByEmail(String email);
}
