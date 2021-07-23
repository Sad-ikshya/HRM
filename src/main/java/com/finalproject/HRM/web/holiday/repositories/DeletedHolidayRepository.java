package com.finalproject.HRM.web.holiday.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalproject.HRM.web.holiday.entities.DeletedHoliday;

public interface DeletedHolidayRepository extends MongoRepository<DeletedHoliday, String>{

}
