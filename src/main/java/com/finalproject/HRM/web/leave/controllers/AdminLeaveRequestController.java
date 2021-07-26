package com.finalproject.HRM.web.leave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.web.leave.requestDtos.LeaveRequestStatusDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveRequestResponse;
import com.finalproject.HRM.web.leave.services.LeaveRequestService;

@RestController
@RequestMapping("/admin/leave-requests")
public class AdminLeaveRequestController {
	@Autowired
	private LeaveRequestService leaveRequestService;
	
	@GetMapping("/")
	public ResponseEntity<Page<LeaveRequestResponse>> getAllLeaveRequest(@RequestParam(defaultValue = "0") int index,
			@RequestParam(defaultValue = "10") int size) {
		return new ResponseEntity<Page<LeaveRequestResponse>>(leaveRequestService.getAllLeaveRequests(index, size),
				HttpStatus.OK);
	}
	
	@PatchMapping("/set-status/{leaveRequestId}")
	public ResponseEntity<LeaveRequestResponse> updateleaveRequestStatus(@PathVariable String leaveRequestId, @RequestBody LeaveRequestStatusDto leaveRequestStatus){
		return new ResponseEntity<LeaveRequestResponse>(leaveRequestService.updateLeaveStatus(leaveRequestId, leaveRequestStatus),HttpStatus.OK);
	}
	

}