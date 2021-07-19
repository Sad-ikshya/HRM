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
@Getter
@Setter
@ToString
@Builder
public class UserDto {
	private String id;
	private String fullName;
	private String email;
	private String department;
	private String designation;
	private String bio;
	private Date joinedDate;
	private Role role;
	private String photo;

}
