package com.finalproject.HRM.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.entities.DeletedUser;

public interface DeletedUserRepository extends MongoRepository<DeletedUser, String>{

}
