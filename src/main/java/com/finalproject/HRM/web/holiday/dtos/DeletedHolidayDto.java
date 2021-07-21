package com.finalproject.HRM.web.holiday.dtos;

import java.util.Date;

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

public class DeletedHolidayDto {

	private String id;
	private String occasion;
	private Date fromDate;
	private Date toDate;
}
