package com.finalproject.HRM.web.holiday.entities;

import javax.validation.constraints.NotBlank;

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
	@NotBlank(message = "description can not be blank")
	private String description;
	@NotBlank(message = "date can not be blank")
	private Long date;
}
