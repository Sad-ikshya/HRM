package com.finalproject.HRM.web.user.entities;

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

@Document(collection = "deletedUsers")
public class DeletedUser {
	@Id
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
