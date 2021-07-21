package com.finalproject.HRM.web.leave.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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

import com.finalproject.HRM.web.leave.dtos.LeaveRequestDto;
import com.finalproject.HRM.web.leave.services.LeaveRequestService;

@RestController
@RequestMapping("/leave-requests")
public class LeaveRequestController {
	@Autowired
	private LeaveRequestService leaveRequestService;

	@GetMapping()
	public ResponseEntity<Page<LeaveRequestDto>> getAllLeaveRequest(@RequestParam(defaultValue = "0") int index,
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
	public ResponseEntity<String> deleteLeaveRequest(@PathVariable String id) {

		return new ResponseEntity<String>(leaveRequestService.deleteLeaveRequest(id), HttpStatus.OK);
	}

	@GetMapping("/by-employee-id/{employee-id}")
	public ResponseEntity<List<LeaveRequestDto>> leaveDetailByEmployeeid(@PathVariable String employeeId) {
		return new ResponseEntity<List<LeaveRequestDto>>(leaveRequestService.leaveDetailByEmployeeId(employeeId),
				HttpStatus.FOUND);
	}

	@GetMapping("/by-date/{date}")
	public ResponseEntity<Page<LeaveRequestDto>> leaveDetailByDate(@PathVariable String date,
			@RequestParam(defaultValue = "0") int index, @RequestParam(defaultValue = "10") int size) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
		formatter.setTimeZone(TimeZone.getTimeZone("Kathmandu/Nepal"));
		System.out.println("Date=====================>"+formatter.parse(date));
		return new ResponseEntity<Page<LeaveRequestDto>>(
				leaveRequestService.leaveDetailByDate(formatter.parse(date), index, size), HttpStatus.OK);
	}
}
