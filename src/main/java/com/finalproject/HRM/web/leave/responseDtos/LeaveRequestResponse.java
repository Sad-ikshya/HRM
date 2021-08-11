package com.finalproject.HRM.web.leave.responseDtos;

import com.finalproject.HRM.web.leave.dtos.LeaveDto;
import com.finalproject.HRM.web.leave.entities.LeaveType;
import com.finalproject.HRM.web.leave.entities.Status;
import com.finalproject.HRM.web.user.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class LeaveRequestResponse {
	private String id;
	private Long fromDate;
	private Long toDate;
	private String leaveReason;
	private LeaveDto leave;
	private LeaveType leaveType;
	private Status status;
	private int days;
	private String verifiedBy;
	private UserDto employee;
}
