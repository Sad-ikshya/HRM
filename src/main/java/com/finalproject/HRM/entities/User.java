package com.finalproject.HRM.entities;

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
@Document(collection = "users")
@ToString
@Builder
@Getter
@Setter
public class User {
	@Id
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