package com.finalproject.HRM.web.leave.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.finalproject.HRM.web.leave.requestDtos.LeaveRequestDto;
import com.finalproject.HRM.web.leave.requestDtos.LeaveRequestStatusDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveRequestResponse;

public interface LeaveRequestService {
	public Page<LeaveRequestResponse> getAllLeaveRequests(int index, int size);

	public Page<LeaveRequestResponse> employeeGetAllLeaveRequests(int index, int size);

	public LeaveRequestResponse saveLeaveRequest(LeaveRequestDto leaveRequest);

	public LeaveRequestResponse updateLeaveRequestDto(String id, LeaveRequestDto leaveRequest);

	public String deleteLeaveRequest(String id);

	public Page<LeaveRequestResponse> pagedLeaveDetailByEmployeeId(String employeeId, int index, int size);

	public List<LeaveRequestResponse> leaveDetailByEmployeeId(String employeeId);

	public Page<LeaveRequestResponse> leaveDetailByTodayDate(int index, int size) throws java.text.ParseException;

	public LeaveRequestResponse updateLeaveStatus(String leaveRequestId, LeaveRequestStatusDto leaveRequestStatus,
			String adminId);
}
