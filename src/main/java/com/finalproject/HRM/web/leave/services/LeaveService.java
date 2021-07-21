package com.finalproject.HRM.web.leave.services;

import java.util.List;

import com.finalproject.HRM.web.leave.dtos.LeaveDto;

public interface LeaveService {
	public LeaveDto saveLeave(LeaveDto leave);
	public List<LeaveDto> getAllLeaves();
	public LeaveDto getLeaveById(String id);
	public LeaveDto updateLeave(String id, LeaveDto leave);
	public String deleteLeave(String id);

}
