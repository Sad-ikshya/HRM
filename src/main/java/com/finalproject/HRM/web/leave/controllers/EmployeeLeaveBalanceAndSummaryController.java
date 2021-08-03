package com.finalproject.HRM.web.leave.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<LeaveSummaryDto> getLeaveSummary(@PathVariable String employeeId){
		return leaveService.getLeaveSummary(employeeId);
	}
	@GetMapping("/leave-balance/{employeeId}")
	public List<LeaveBalanceDto> getLeaveBalance(@PathVariable String employeeId){
		return leaveService.getLeaveBalance(employeeId);
	}

}
