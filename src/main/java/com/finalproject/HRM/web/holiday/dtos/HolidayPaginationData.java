package com.finalproject.HRM.web.holiday.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class HolidayPaginationData {
	private int totalPage;
	private int currentPage;
	private List<HolidayDto> holidayData;
}
