package com.finalproject.HRM.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.HRM.web.holiday.dtos.HolidayPaginationData;
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
	public HolidayPaginationData getUpcomingHoliday(
							@RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
							@RequestParam(name = "limit", defaultValue = "2") int limit,
							@RequestParam(name = "sortBy", defaultValue = "id") String sortBy
							) 
	{
		return holidayService.getUpcomingHoliday(pageNo, limit, sortBy);
	}

	@GetMapping("/employees-on-leave")
	public Page<LeaveRequestResponse> leaveDetailByTodayDate(
			@RequestParam(defaultValue = "0") int index, @RequestParam(defaultValue = "10") int size) throws ParseException{
		return leaveRequestService.leaveDetailByTodayDate(index, size);
	}
}
