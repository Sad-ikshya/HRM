package com.finalproject.HRM.dto;

import java.util.Date;

import com.finalproject.HRM.entity.User;

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

public class UserDto {
	
	private String id;
	private String fullName;
	private String image;
	private String email;
	private String role;
	private String department;
	private String designation;
	private String bio;
	private Date joinedDate;
}
