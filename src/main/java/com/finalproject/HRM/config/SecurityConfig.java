package com.finalproject.HRM.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.finalproject.HRM.security.oauth2.CustomOAuth2Service;
import com.finalproject.HRM.security.oauth2.OAuth2LoginSuccessHandeler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	CustomOAuth2Service customOauth2Service;
	
	@Autowired
	OAuth2LoginSuccessHandeler oauth2uccessHandeler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/oauth/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.oauth2Login()
				.userInfoEndpoint().userService(customOauth2Service)
				.and()
				.successHandler(oauth2uccessHandeler);
			
	}
}
