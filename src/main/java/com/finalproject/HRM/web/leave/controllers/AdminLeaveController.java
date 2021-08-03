package com.finalproject.HRM.web.leave.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admins/{adminId}/leaves")
public class AdminLeaveController {
	@Autowired
	private LeaveService leaveService;

	@PostMapping("/")

	public LeaveDto saveLeave(@PathVariable String adminId,@RequestBody LeaveDto leave) {
		return leaveService.saveLeave(leave);
	}

	@GetMapping()
	public List<LeaveDto> getAllLeaves(@PathVariable String adminId) {
		return leaveService.getAllLeaves();
	}

	@GetMapping("/{id}")
	public LeaveDto getLeaveById(@PathVariable String adminId,@PathVariable String id) {
		return leaveService.getLeaveById(id);
	}

	@PutMapping("/{id}")
	public LeaveDto updateLeave(@PathVariable String adminId,@PathVariable String id, @RequestBody LeaveDto leave) {
		return leaveService.updateLeave(id, leave);
	}

	@DeleteMapping("/{id}")
	public String deleteLeave(@PathVariable String adminId,@PathVariable String id) {
		return leaveService.deleteLeave(id);
	}

}
