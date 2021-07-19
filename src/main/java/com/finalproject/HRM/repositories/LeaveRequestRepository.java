package com.finalproject.HRM.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.entities.LeaveRequest;

public interface LeaveRequestRepository extends MongoRepository<LeaveRequest, String>{

}
