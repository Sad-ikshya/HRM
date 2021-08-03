package com.finalproject.HRM.web.leave.repositories;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.finalproject.HRM.web.leave.entities.LeaveRequest;

public interface LeaveRequestRepository extends MongoRepository<LeaveRequest, String> {
	public Page<LeaveRequest> findByEmployeeId(String employeeId, Pageable pageable);

	public List<LeaveRequest> findByEmployeeId(String employeeId);

	@Query("{'fromDate':?0}")
	public Page<LeaveRequest> findByFromDate(BigInteger date, Pageable pageable);
}
