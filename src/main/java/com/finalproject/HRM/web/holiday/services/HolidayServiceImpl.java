package com.finalproject.HRM.web.holiday.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.web.holiday.dtos.HolidayDto;
import com.finalproject.HRM.web.holiday.dtos.HolidayPaginationData;
import com.finalproject.HRM.web.holiday.entities.DeletedHoliday;
import com.finalproject.HRM.web.holiday.entities.Holiday;
import com.finalproject.HRM.web.holiday.repositories.DeletedHolidayRepository;
import com.finalproject.HRM.web.holiday.repositories.HolidayRepository;
import com.finalproject.HRM.web.user.entities.User;

@Service
public class HolidayServiceImpl implements HolidayService{

	@Autowired
	HolidayRepository holidayRepo;
	
	@Autowired
	DeletedHolidayRepository deletedHolidayRepo;
	
	public Page<Holiday> pagination(int pageNo, int limit, String sortBy) {
		Page<Holiday> holidayData;
		Sort sort = Sort.by(sortBy);
		Pageable Page = PageRequest.of(pageNo, limit, sort);

		holidayData = holidayRepo.findAll(Page);
		
		return holidayData;
	}
	
	@Override
	public HolidayPaginationData getAllHoliday(int pageNo, int limit, String sortBy) {
		Page<Holiday> holidayData = pagination(pageNo, limit, sortBy);
		List<Holiday> holidayList = holidayData.getContent();
		List<HolidayDto> holidayDtoList = new ArrayList<>();
		
		for(Holiday holiday:holidayList)
		{
			HolidayDto holidayDto = HolidayDto.builder().id(holiday.getId())
													.description(holiday.getDescription())
													.date(holiday.getDate())
													.build();
			holidayDtoList.add(holidayDto);
		}
		
		HolidayPaginationData paginationData = HolidayPaginationData.builder()
												.currentPage(holidayData.getNumber())
												.totalPage(holidayData.getSize())
												.holidayData(holidayDtoList)
												.build();
		return paginationData;
	}

	@Override
	public HolidayDto getHolidayById(String id) {
		Holiday holiday = holidayRepo.findById(id)
							.orElseThrow(()->new IllegalStateException("Holiday data with id :"+id+" not found"));
		
		HolidayDto holidayDto = HolidayDto.builder().id(holiday.getId())
												.description(holiday.getDescription())
												.date(holiday.getDate())
												.build();
		return holidayDto;
	}

	@Override
	public HolidayDto addHoliday(HolidayDto holiday) {
		Holiday holidayEntity = Holiday.builder()
										.description(holiday.getDescription())
										.date(holiday.getDate())
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
										.description(holiday.getDescription())
										.date(holiday.getDate())
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
													.description(holiday.getDescription())
													.date(holiday.getDate())
													.build();

		deletedHolidayRepo.save(deletedHoliday);
		
		//delete holiday by id
		holidayRepo.deleteById(id);
		
		return "Holiday with id:"+id+" deleted successfully";
	}

	@Override
	public HolidayPaginationData getUpcomingHoliday(int pageNo, int limit, String sortBy) {
		Date today = new Date();
		
		Page<Holiday> holidayData;
		Sort sort = Sort.by(sortBy);
		Pageable Page = PageRequest.of(pageNo, limit, sort);

		holidayData = holidayRepo.findByDateGreaterThan(today,Page);
		
		List<Holiday> holidayList = holidayData.getContent();
		List<HolidayDto> holidayDtoList = new ArrayList<>();
		
		for(Holiday holiday:holidayList)
		{
			HolidayDto holidayDto = HolidayDto.builder().id(holiday.getId())
													.description(holiday.getDescription())
													.date(holiday.getDate())
													.build();
			holidayDtoList.add(holidayDto);
		}
		
		HolidayPaginationData paginationData = HolidayPaginationData.builder()
											.currentPage(holidayData.getNumber())
											.totalPage(holidayData.getSize())
											.holidayData(holidayDtoList)
											.build();
		return paginationData;
	}

}
