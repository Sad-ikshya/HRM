package com.finalproject.HRM.web.leave.dtos;

import java.util.Date;

import com.finalproject.HRM.web.leave.entities.LeaveType;
import com.finalproject.HRM.web.leave.entities.Status;

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
public class LeaveRequestDto {
	private String id;
	private Date fromDate;
	private Date toDate;
	private String leaveReason;
	private LeaveDto leave;
	private LeaveType leaveType;
	private Status status;
	private UserDto employee;

}
