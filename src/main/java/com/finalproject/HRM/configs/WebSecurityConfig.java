package com.finalproject.HRM.configs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.finalproject.HRM.entities.CustomOAuth2User;
import com.finalproject.HRM.implementations.CustomOAuth2UserService;
import com.finalproject.HRM.implementations.UserService;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private CustomOAuth2UserService oauthUserService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OAuth2LoginSucessHandler oAuth2LoginSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/","/login","/oauth/**").permitAll()
		.and().oauth2Login().userInfoEndpoint()
		.userService(oauthUserService)
		.and()
		.successHandler(oAuth2LoginSuccessHandler).failureHandler(new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				System.out.println("Error during oauth");
				
			}
			
		});
			
			
		
	}
	

}
