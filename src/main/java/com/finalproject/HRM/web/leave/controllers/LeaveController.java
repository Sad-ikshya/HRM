package com.finalproject.HRM.web.leave.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PostMapping("/")
	public ResponseEntity<LeaveDto> saveLeave(@RequestBody LeaveDto leave) {
		return new ResponseEntity<LeaveDto>(leaveService.saveLeave(leave), HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<LeaveDto>> getAllLeaves() {
		return new ResponseEntity<List<LeaveDto>>(leaveService.getAllLeaves(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LeaveDto> getLeaveById(@PathVariable String id) {
		return new ResponseEntity<LeaveDto>(leaveService.getLeaveById(id), HttpStatus.FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<LeaveDto> updateLeave(@PathVariable String id, @RequestBody LeaveDto leave) {
		return new ResponseEntity<LeaveDto>(leaveService.updateLeave(id, leave), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLeave(@PathVariable String id) {
		return new ResponseEntity<String>(leaveService.deleteLeave(id), HttpStatus.OK);
	}
}
