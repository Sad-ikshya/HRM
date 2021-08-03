package com.finalproject.HRM.web.leave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.web.leave.requestDtos.LeaveRequestDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveRequestResponse;
import com.finalproject.HRM.web.leave.services.LeaveRequestService;

@RestController
@RequestMapping("/leave-requests")
public class LeaveRequestController {
	@Autowired
	private LeaveRequestService leaveRequestService;

	@GetMapping
	public Page<LeaveRequestResponse> getAllLeaveRequest(@RequestParam(defaultValue = "0") int index,
			@RequestParam(defaultValue = "10") int size) {
		return leaveRequestService.getAllLeaveRequests(index, size);
	}

	@PostMapping("/")
	public LeaveRequestResponse saveLeaveRequest(@RequestBody LeaveRequestDto leaveRequest) {
		return leaveRequestService.saveLeaveRequest(leaveRequest);
	}

	@PutMapping("/{id}")
	public LeaveRequestResponse updateLeaveRequest(@PathVariable String id, @RequestBody LeaveRequestDto leaveRequest) {
		return leaveRequestService.updateLeaveRequestDto(id, leaveRequest);
	}

	@DeleteMapping("/{id}")
	public String deleteLeaveRequest(@PathVariable String id) {

		return leaveRequestService.deleteLeaveRequest(id);
	}

	@GetMapping("/employee-id/{employeeId}")
	public Page<LeaveRequestResponse> leaveDetailByEmployeeid(@PathVariable String employeeId,
			@RequestParam(defaultValue = "0") int index, @RequestParam(defaultValue = "10") int size) {
		return leaveRequestService.pagedLeaveDetailByEmployeeId(employeeId, index, size);
	}
}
