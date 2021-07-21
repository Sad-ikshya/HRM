package com.finalproject.HRM.web.leave.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.finalproject.HRM.web.leave.dtos.LeaveRequestDto;

public interface LeaveRequestService {
	public Page<LeaveRequestDto> getAllLeaveRequests(int index, int size);

	public LeaveRequestDto saveLeaveRequest(LeaveRequestDto leaveRequest);

	public LeaveRequestDto updateLeaveRequestDto(String id, LeaveRequestDto leaveRequest);

	public String deleteLeaveRequest(String id);

	public List<LeaveRequestDto> leaveDetailByEmployeeId(String employeeId);
	
	public Page<LeaveRequestDto> leaveDetailByDate(Date date,int index, int size);

}
