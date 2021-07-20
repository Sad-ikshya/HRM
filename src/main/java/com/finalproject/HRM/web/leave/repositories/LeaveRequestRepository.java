package com.finalproject.HRM.web.leave.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.web.leave.entities.LeaveRequest;

public interface LeaveRequestRepository extends MongoRepository<LeaveRequest, String>{

}
