package com.finalproject.HRM.web;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.web.holiday.dtos.HolidayDto;
import com.finalproject.HRM.web.holiday.services.HolidayService;
import com.finalproject.HRM.web.leave.responseDtos.LeaveRequestResponse;
import com.finalproject.HRM.web.leave.services.LeaveRequestService;

@RestController
public class DashboardController {

	@Autowired
	HolidayService holidayService;

	@Autowired
	private LeaveRequestService leaveRequestService;

	@GetMapping("/upcoming-holiday")
	public List<HolidayDto> getUpcomingHoliday() {
		return holidayService.getUpcomingHoliday();
	}

	@GetMapping("/employees-on-leave")
	public Page<LeaveRequestResponse> leaveDetailByTodayDate(
			@RequestParam(defaultValue = "0") int index, @RequestParam(defaultValue = "10") int size){
		Long todayDate=new Date().getTime();
		return leaveRequestService.leaveDetailByTodayDate(todayDate, index, size);
	}
}
