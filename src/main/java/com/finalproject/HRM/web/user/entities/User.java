package com.finalproject.HRM.web.user.entities;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@NotBlank(message = "Full Name can not be blank")
	@Size(min = 3 , message = "Full Name should contain atleast 3 character")
	private String fullName;
	@NotNull(message = "email address required")
	@Email(message = "invalid email address")
	private String email;
	private String department;
	private String designation;
	private String bio;
	private Date joinedDate;
	@NotBlank(message = "Role can not be blank")
	private Role role;
	private String photo;
	

}
