package com.finalproject.HRM.web.leave.responseDtos;

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
public class LeaveSummaryDto {
	private String id;
	private String leaveName;
	private Integer total;
	private float use;
	private float balance;
	private String employeeId;
}
