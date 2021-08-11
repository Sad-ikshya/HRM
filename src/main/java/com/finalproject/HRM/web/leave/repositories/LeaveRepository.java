package com.finalproject.HRM.web.leave.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.web.leave.entities.Leave;

public interface LeaveRepository extends MongoRepository<Leave, String> {

}
