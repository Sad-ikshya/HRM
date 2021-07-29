package com.finalproject.HRM.web.holiday.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.web.holiday.dtos.HolidayDto;
import com.finalproject.HRM.web.holiday.services.HolidayService;

@RestController
@RequestMapping("/admin/holidays")
public class AdminHolidayController {

	@Autowired
	HolidayService holidayService;
	
	@GetMapping
	public HolidayDto dummyGetHoliday()
	{
		HolidayDto resp = new HolidayDto("dummy", "dummy", new Date());
		return resp;
	}
	
	@GetMapping("/{id}")
	public HolidayDto getHolidayByID(@PathVariable String id)
	{
		return holidayService.getHolidayById(id);
	}
	
	@PostMapping
	public HolidayDto addHoliday(@RequestBody HolidayDto holiday)
	{
		return holidayService.addHoliday(holiday);
	}
	
	@PutMapping("/{id}")
	public HolidayDto updateHoliday(@PathVariable String id,@RequestBody HolidayDto holiday)
	{
		return holidayService.updateHoliday(id, holiday);
	}
	
	@DeleteMapping("/{id}")
	public String deleteHoliday(@PathVariable String id)
	{
		return holidayService.deleteHoliday(id);
	}
}
