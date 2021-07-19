package com.finalproject.HRM.entities;

import java.util.Date;

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
	private Date fromDate;
	private Date toDate;
	private String leaveReason;
	private Leave leave;
	private LeaveType leaveType;
	private Status status;
	private User employee;
	
	

}
