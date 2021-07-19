package com.finalproject.HRM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.dtos.LeaveRequestDto;
import com.finalproject.HRM.services.LeaveRequestService;

@RestController
@RequestMapping("/leaveRequests")
public class LeaveRequestController {
	@Autowired
	private LeaveRequestService leaveRequestService;

	@GetMapping()
	public ResponseEntity<Page<LeaveRequestDto>> getAllLeaveRequest(
			@RequestParam(defaultValue = "0") int index,
			@RequestParam(defaultValue = "10") int size) {
		return new ResponseEntity<Page<LeaveRequestDto>>(leaveRequestService.getAllLeaveRequests(index, size),
				HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<LeaveRequestDto> saveLeaveRequest(@RequestBody LeaveRequestDto leaveRequest) {
		return new ResponseEntity<LeaveRequestDto>(leaveRequestService.saveLeaveRequest(leaveRequest),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<LeaveRequestDto> updateLeaveRequest(@PathVariable String id,
			@RequestBody LeaveRequestDto leaveRequest) {
		return new ResponseEntity<LeaveRequestDto>(leaveRequestService.updateLeaveRequestDto(id, leaveRequest),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteLeaveRequest(@PathVariable String id) {
		leaveRequestService.deleteLeaveRequest(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
