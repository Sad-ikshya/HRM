package com.finalproject.HRM.web.holiday.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.web.holiday.entities.Holiday;

public interface HolidayRepository extends MongoRepository<Holiday, String>{
	public List<Holiday> findByDateGreaterThan(Date date);
}
