package com.finalproject.HRM.web.leave.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.finalproject.HRM.web.user.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "deletedLeaveRequests")
@ToString
@Builder
@Getter
@Setter
public class DeletedLeaveRequest {
	@Id
	private String id;
	private Date fromDate;
	private Date toDate;
	private String leaveReason;
	private String leaveId;
	private LeaveType leaveType;
	private Status status;
	private User employee;

}
