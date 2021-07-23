package com.finalproject.HRM.web.holiday.services;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.finalproject.HRM.web.holiday.dtos.HolidayDto;
import com.finalproject.HRM.web.holiday.entities.DeletedHoliday;
import com.finalproject.HRM.web.holiday.entities.Holiday;
import com.finalproject.HRM.web.holiday.repositories.DeletedHolidayRepository;
import com.finalproject.HRM.web.holiday.repositories.HolidayRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class HolidayServiceImplTest {

	@InjectMocks
	private HolidayServiceImpl holidayServiceImpl;
	
	@Mock
	private HolidayRepository holidayRepo;
	
	@Mock
	private DeletedHolidayRepository deletedHolidayRepo;
	
	@Before
	public void setup() {
	  
	}
	
	@Test
	public void testAddHoliday() {
	       
		Holiday holiday = new Holiday("abc", "gai jatra",new Date(2021 - 07 - 15));
		when(holidayRepo.insert(holiday)).thenReturn(holiday);
		
		HolidayDto holidaydto = new HolidayDto(null, "gai jatra", new Date(2021 - 07 - 15));
		HolidayDto result = holidayServiceImpl.addHoliday(holidaydto);
		
		assertEquals(holidaydto, result);
	}

	@Test
	public void testGetAllHoliday()
	{
		List<Holiday> holidayList = new ArrayList<>();
		holidayList.add(new Holiday("1", "gai jatra",new Date(2021 - 7 - 15)));
		holidayList.add(new Holiday("2", "jatra",new Date(2021 - 5 - 10)));
		holidayList.add(new Holiday("3", "tihar",new Date(2021 - 9 - 5)));
		when(holidayRepo.findAll()).thenReturn(holidayList);
		
		List<HolidayDto> result = holidayServiceImpl.getAllHoliday();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetHolidayById()
	{
		Holiday holiday = new Holiday("1", "gai jatra",new Date(2021 - 7 - 15));
		when(holidayRepo.findById("1")).thenReturn(Optional.of(holiday));
		
		HolidayDto holidayDto = new HolidayDto("1", "gai jatra",new Date(2021 - 7 - 15));
		HolidayDto result = holidayServiceImpl.getHolidayById("1");
		
		assertEquals("gai jatra", result.getDescription());
		assertEquals(new Date(2021 - 07 - 15), result.getDate());
//		assertEquals(holidayDto, result);
	}
	
	@Test
	public void testUpdateHoliday() {
	       
		Holiday holiday = new Holiday("abc", "gai jatra",new Date(2021 - 07 - 15));
		when(holidayRepo.findById("abc")).thenReturn(Optional.of(holiday));
		when(holidayRepo.save(holiday)).thenReturn(holiday);
		
		HolidayDto holidaydto = new HolidayDto(null, "gai jatra", new Date(2021 - 07 - 15));
		HolidayDto result = holidayServiceImpl.updateHoliday("abc", holidaydto);
		assertEquals(holidaydto, result);
	}
	
	@Test
	public void testDeleteHoliday()
	{
		Holiday holiday = new Holiday("abc", "gai jatra",new Date(2021 - 07 - 15));
		DeletedHoliday deletedHoliday = new DeletedHoliday
					("abc", "gai jatra",new Date(2021 - 07 - 15));
		
		when(holidayRepo.findById("abc")).thenReturn(Optional.of(holiday));
		when(deletedHolidayRepo.save(deletedHoliday)).thenReturn(deletedHoliday);
		holidayServiceImpl.deleteHoliday("abc");
		verify(holidayRepo, times(1)).deleteById("abc");
		
		String result = holidayServiceImpl.deleteHoliday("abc");
		assertEquals("Holiday with id:abc deleted successfully", result);
	}
}
