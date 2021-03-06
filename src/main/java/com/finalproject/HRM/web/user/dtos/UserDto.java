package com.finalproject.HRM.web.user.dtos;

import java.util.List;

import com.finalproject.HRM.web.user.entities.Role;

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
	private Long joinedDate;
	private List<Role>  roles;
	private String photo;
	private Long contact;
}
