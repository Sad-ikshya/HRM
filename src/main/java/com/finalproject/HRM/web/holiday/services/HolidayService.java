package com.finalproject.HRM.web.holiday.services;

import java.util.List;

import com.finalproject.HRM.web.holiday.dtos.HolidayDto;
import com.finalproject.HRM.web.holiday.dtos.HolidayPaginationData;

public interface HolidayService {
	public HolidayPaginationData getAllHoliday(int pageNo, int limit, String sortBy);
	public HolidayDto getHolidayById(String id);
	public HolidayDto addHoliday(HolidayDto holiday);
	public HolidayDto updateHoliday(String id,HolidayDto holiday);
	public String deleteHoliday(String id);
	public List<HolidayDto> getUpcomingHoliday();
}
