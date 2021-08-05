package com.finalproject.HRM.web.holiday.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.web.holiday.entities.Holiday;

public interface HolidayRepository extends MongoRepository<Holiday, String>{
	public Page<Holiday> findByDateGreaterThan(long date,Pageable page);
}
