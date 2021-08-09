package com.finalproject.HRM.web.user.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
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
	private Long joinedDate;
	@NotBlank(message = "Role can not be blank")
	private List<Role>  roles;
	private String photo;
	private Long contact;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.roles.toString());
		Set<GrantedAuthority> auths = new HashSet<>();
		auths.add(authority);
		return auths;
	}
	@Override
	public String getPassword() {
		return null;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	

}
