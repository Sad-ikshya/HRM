package com.finalproject.HRM.web.leave.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.services.LeaveService;

@RestController
@RequestMapping("leaves/leave-requests")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;

	@GetMapping
	public List<LeaveDto> getAllLeaves() {
		return leaveService.getAllLeaves();
	}

}
