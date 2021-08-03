package com.finalproject.HRM.web.holiday.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.web.holiday.dtos.HolidayDto;
import com.finalproject.HRM.web.holiday.dtos.HolidayPaginationData;
import com.finalproject.HRM.web.holiday.services.HolidayService;

@RestController
@RequestMapping("/admins/{adminid}/holidays")
public class AdminHolidayController {

	@Autowired
	HolidayService holidayService;
	
	@GetMapping
	public HolidayPaginationData getAllHoliday(
								@PathVariable String adminid,
								@RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
								@RequestParam(name = "limit", defaultValue = "2") int limit,
								@RequestParam(name = "sortBy", defaultValue = "id") String sortBy
									)
	{
		return holidayService.getAllHoliday(pageNo, limit, sortBy);
	}
	
	
	@GetMapping("/{id}")
	public HolidayDto getHolidayByID(@PathVariable String adminid,@PathVariable String id)
	{
		return holidayService.getHolidayById(id);
	}
	
	@PostMapping
	public HolidayDto addHoliday(@PathVariable String adminid,@RequestBody HolidayDto holiday)
	{
		return holidayService.addHoliday(holiday);
	}
	
	@PutMapping("/{id}")
	public HolidayDto updateHoliday(@PathVariable String adminid,@PathVariable String id,@RequestBody HolidayDto holiday)
	{
		return holidayService.updateHoliday(id, holiday);
	}
	
	@DeleteMapping("/{id}")
	public String deleteHoliday(@PathVariable String adminid,@PathVariable String id)
	{
		return holidayService.deleteHoliday(id);
	}
}
