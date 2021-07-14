package com.finalproject.HRM.security.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.finalproject.HRM.dto.UserDto;
import com.finalproject.HRM.service.UserService;

@Component

public class OAuth2LoginSuccessHandeler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Autowired
	UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
//		CustomOAuth2User user = (CustomOAuth2User) authentication.getPrincipal();
//		
//		String email = user.getemail();
//		String fullName = user.getfullName();
//		
//		System.out.println("Email : "+email);
//		System.out.println("Full Name : "+fullName);
		
//		UserDto userDto = UserDto.builder().fullName(user.getfullName())
//										.email(user.getemail())
//										.role("employee")
//										.build();
//		userService.addUser(userDto);
		
//		System.out.println(request.getParameter("email"));
		
		System.out.println("auth successfull");
		response.sendRedirect("/signup");
	}
}
