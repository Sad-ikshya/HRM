package com.finalproject.HRM.web.leave.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.web.leave.responseDtos.LeaveBalanceDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveSummaryDto;
import com.finalproject.HRM.web.leave.services.LeaveService;

@RestController
@RequestMapping("/leaves")
public class EmployeeLeaveBalanceAndSummaryController {
	@Autowired
	private LeaveService leaveService;
	
	@GetMapping("/leave-summary/{employeeId}")
	public ResponseEntity<List<LeaveSummaryDto>> getLeaveSummary(@PathVariable String employeeId){
		return new ResponseEntity<List<LeaveSummaryDto>>(leaveService.getLeaveSummary(employeeId), HttpStatus.OK);
	}
	@GetMapping("/leave-balance/{employeeId}")
	public ResponseEntity<List<LeaveBalanceDto>> getLeaveBalance(@PathVariable String employeeId){
		return new ResponseEntity<List<LeaveBalanceDto>>(leaveService.getLeaveBalance(employeeId), HttpStatus.OK);
	}

}
