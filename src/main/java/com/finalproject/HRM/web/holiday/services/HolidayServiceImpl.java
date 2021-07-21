package com.finalproject.HRM.web.holiday.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.web.holiday.dtos.HolidayDto;
import com.finalproject.HRM.web.holiday.entities.DeletedHoliday;
import com.finalproject.HRM.web.holiday.entities.Holiday;
import com.finalproject.HRM.web.holiday.repositories.DeletedHolidayRepository;
import com.finalproject.HRM.web.holiday.repositories.HolidayRepository;

@Service
public class HolidayServiceImpl implements HolidayService{

	@Autowired
	HolidayRepository holidayRepo;
	
	@Autowired
	DeletedHolidayRepository deletedHolidayRepo;
	
	@Override
	public List<HolidayDto> getAllHoliday() {
		List<Holiday> holidayList = holidayRepo.findAll();
		List<HolidayDto> holidayDtoList = new ArrayList<>();
		
		for(Holiday holiday:holidayList)
		{
			HolidayDto holidayDto = HolidayDto.builder().id(holiday.getId())
													.occasion(holiday.getOccasion())
													.fromDate(holiday.getFromDate())
													.toDate(holiday.getToDate())
													.build();
			holidayDtoList.add(holidayDto);
		}
		return holidayDtoList;
	}

	@Override
	public HolidayDto getHolidayById(String id) {
		Holiday holiday = holidayRepo.findById(id)
							.orElseThrow(()->new IllegalStateException("Holiday data with id :"+id+" not found"));
		
		HolidayDto holidayDto = HolidayDto.builder().id(holiday.getId())
												.occasion(holiday.getOccasion())
												.fromDate(holiday.getFromDate())
												.toDate(holiday.getToDate())
												.build();
		return holidayDto;
	}

	@Override
	public HolidayDto addHoliday(HolidayDto holiday) {
		Holiday holidayEntity = Holiday.builder()
										.occasion(holiday.getOccasion())
										.fromDate(holiday.getFromDate())
										.toDate(holiday.getToDate())
										.build();
		
		holidayRepo.insert(holidayEntity);
		return holiday;
	}

	@Override
	public HolidayDto updateHoliday(String id, HolidayDto holiday) {
		
		//check if data with id is present or not
		Holiday holidayEntity = holidayRepo.findById(id)
				.orElseThrow(()->new IllegalStateException("Holiday data with id :"+id+" not found"));
		
		holidayEntity = Holiday.builder().id(id)
								.occasion(holiday.getOccasion())
								.fromDate(holiday.getFromDate())
								.toDate(holiday.getToDate())
								.build();

		holidayRepo.save(holidayEntity);
		return holiday;
	}

	@Override
	public String deleteHoliday(String id) {
		Holiday holiday = holidayRepo.findById(id)
				.orElseThrow(()->new IllegalStateException("Holiday data with id :"+id+" not found"));
		
		//save deleted holiday data into deleted_holiday collection
		DeletedHoliday deletedHoliday = DeletedHoliday.builder().id(id)
								.occasion(holiday.getOccasion())
								.fromDate(holiday.getFromDate())
								.toDate(holiday.getToDate())
								.build();

		deletedHolidayRepo.save(deletedHoliday);
		
		//delete holiday by id
		holidayRepo.deleteById(id);
		
		return "Holiday with id:"+id+" deleted successfully";
	}

}
