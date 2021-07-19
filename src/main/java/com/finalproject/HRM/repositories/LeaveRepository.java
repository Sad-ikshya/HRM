package com.finalproject.HRM.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.entities.Leave;

public interface LeaveRepository extends MongoRepository<Leave, String>{

}
