package com.finalproject.HRM.web.leave.entities;

import java.util.Date;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@Document(collection = "leaveRequests")
@ToString
@Builder
@Getter
@Setter
public class LeaveRequest {
	@Id
	private String id;
	@FutureOrPresent
	private Date fromDate;
	@FutureOrPresent
	private Date toDate;
	@NotBlank(message = "Leave reason should not be blank")
	@Size(min = 20, max = 80)
	private String leaveReason;
	@NotBlank(message = "Leave id can not be blank")
	private String leaveId;
	private LeaveType leaveType;
	private Status status;
	@NotBlank(message = "Employee id cannot be blank")
	private String employeeId;

}
