package com.finalproject.HRM.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public List<HolidayDto> getUpcomingHoliday()
	{
		return holidayService.getUpcomingHoliday();
	}
	
	@GetMapping("/date/{date}")
	public ResponseEntity<Page<LeaveRequestResponse>> leaveDetailByDate(@PathVariable String date,
			@RequestParam(defaultValue = "0") int index, @RequestParam(defaultValue = "10") int size) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
		formatter.setTimeZone(TimeZone.getTimeZone("Kathmandu/Nepal"));
		
		return new ResponseEntity<Page<LeaveRequestResponse>>(
				leaveRequestService.leaveDetailByDate(formatter.parse(date), index, size), HttpStatus.OK);
	}
}
