package com.finalproject.HRM.web.leave.requestDtos;

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
public class LeaveRequestStatusDto {
	private Status status;

}
