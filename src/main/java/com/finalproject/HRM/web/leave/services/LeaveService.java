package com.finalproject.HRM.web.leave.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveBalanceDto;
import com.finalproject.HRM.web.leave.responseDtos.LeaveBalanceResponse;
import com.finalproject.HRM.web.leave.responseDtos.LeaveSummaryDto;

public interface LeaveService {
	public LeaveDto saveLeave(LeaveDto leave);

	public List<LeaveDto> getAllLeaves();

	public LeaveDto getLeaveById(String id);

	public LeaveDto updateLeave(String id, LeaveDto leave);

	public String deleteLeave(String id);

	public List<LeaveSummaryDto> getLeaveSummary(String employeeId);

	public Page<LeaveSummaryDto> getPagedLeaveSummary(String employeeId, int index, int size);

	public List<LeaveBalanceDto> getLeaveBalance(String employeeId);
	
	public LeaveBalanceResponse getTotalLeaveBalance(String employeeId);
}
