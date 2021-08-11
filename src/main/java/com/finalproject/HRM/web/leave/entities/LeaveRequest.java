package com.finalproject.HRM.web.leave.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "leaveRequests")
@ToString
@Builder
@Getter
@Setter
public class LeaveRequest {
	@Id
	private String id;
	private Long fromDate;
	private Long toDate;
	@NotBlank(message = "Leave reason should not be blank")
	@Size(min = 20, max = 80)
	private String leaveReason;
	@NotBlank(message = "Leave id can not be blank")
	private String leaveId;
	private LeaveType leaveType;
	private Status status;
	@NotBlank(message = "Employee id cannot be blank")
	private int days;
	private String verfiedBy;
	private String employeeId;

}
