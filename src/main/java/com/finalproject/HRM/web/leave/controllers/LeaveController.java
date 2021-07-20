package com.finalproject.HRM.web.leave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.services.LeaveService;

@RestController
@RequestMapping("/leaves")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;

	@PostMapping()
	public ResponseEntity<LeaveDto> saveLeave(@RequestBody LeaveDto leave) {
		return new ResponseEntity<LeaveDto>(leaveService.saveLeave(leave), HttpStatus.CREATED);
	}

}
