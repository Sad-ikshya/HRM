package com.finalproject.HRM.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.finalproject.HRM.dtos.LeaveRequestDto;

public interface LeaveRequestService {
	public Page<LeaveRequestDto> getAllLeaveRequests(int index, int size);
	public LeaveRequestDto saveLeaveRequest(LeaveRequestDto leaveRequest);
	public LeaveRequestDto updateLeaveRequestDto(String id, LeaveRequestDto leaveRequest);
	public void deleteLeaveRequest(String id);
	

}
