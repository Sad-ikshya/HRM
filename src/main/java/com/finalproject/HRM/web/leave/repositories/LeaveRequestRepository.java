package com.finalproject.HRM.web.leave.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.finalproject.HRM.web.leave.entities.LeaveRequest;

public interface LeaveRequestRepository extends MongoRepository<LeaveRequest, String> {
	public Page<LeaveRequest> findByEmployeeId(String employeeId, Pageable pageable);

	public List<LeaveRequest> findByEmployeeId(String employeeId);

}
