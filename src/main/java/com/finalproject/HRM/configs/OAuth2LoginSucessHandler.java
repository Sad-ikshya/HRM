package com.finalproject.HRM.configs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.finalproject.HRM.dtos.UserDto;
import com.finalproject.HRM.entities.CustomOAuth2User;
import com.finalproject.HRM.entities.User;
import com.finalproject.HRM.services.UserService;

@Component
public class OAuth2LoginSucessHandler extends SimpleUrlAuthenticationSuccessHandler{
	@Autowired
	private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	throws IOException, ServletException{
		CustomOAuth2User oAuth2User= (CustomOAuth2User) authentication.getPrincipal();
		String email=oAuth2User.getEmail();
		
		UserDto user=userService.getUserByEmail(email);
		
		super.onAuthenticationSuccess(request, response, authentication);
		
	}

}
