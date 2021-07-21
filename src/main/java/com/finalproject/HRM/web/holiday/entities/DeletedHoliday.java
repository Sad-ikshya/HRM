package com.finalproject.HRM.web.holiday.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter

@Document("deletedHolidays")
public class DeletedHoliday {
	@Id
	private String id;
	private String occasion;
	private Date fromDate;
	private Date toDate;
}
