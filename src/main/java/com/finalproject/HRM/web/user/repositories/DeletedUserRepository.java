package com.finalproject.HRM.web.user.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.web.user.entities.DeletedUser;

public interface DeletedUserRepository extends MongoRepository<DeletedUser, String>{

}
