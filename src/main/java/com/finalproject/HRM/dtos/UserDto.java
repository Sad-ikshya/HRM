package com.finalproject.HRM.dtos;

import java.util.Date;


import com.finalproject.HRM.entities.Role;

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
	private String firstname;
	private String lastname;
	private String email;
	private String department;
	private String designation;
	private String bio;
	private Date joinedDate;
	private Role role;
	private String photo;
	private String provider;

}