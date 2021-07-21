package com.finalproject.HRM.web.leave.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.finalproject.HRM.web.leave.requestDtos.LeaveRequestDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveRequestResponse;

public interface LeaveRequestService {
	public Page<LeaveRequestResponse> getAllLeaveRequests(int index, int size);

	public LeaveRequestResponse saveLeaveRequest(LeaveRequestDto leaveRequest);

	public LeaveRequestResponse updateLeaveRequestDto(String id, LeaveRequestDto leaveRequest);

	public String deleteLeaveRequest(String id);

	public List<LeaveRequestResponse> leaveDetailByEmployeeId(String employeeId);
	
	public Page<LeaveRequestResponse> leaveDetailByDate(Date date,int index, int size);

}
