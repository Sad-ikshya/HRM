package com.finalproject.HRM.web.holiday.services;

import java.util.List;

import com.finalproject.HRM.web.holiday.dtos.HolidayDto;

public interface HolidayService {
	public List<HolidayDto> getAllHoliday();
	public HolidayDto getHolidayById(String id);
	public HolidayDto addHoliday(HolidayDto holiday);
	public HolidayDto updateHoliday(String id,HolidayDto holiday);
	public String deleteHoliday(String id);
}
