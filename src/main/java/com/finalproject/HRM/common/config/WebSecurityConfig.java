package com.finalproject.HRM.common.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.finalproject.HRM.common.security.oAuth2.CustomOidcUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomOidcUserService customOidcUserService;

	@Autowired
	private GoogleIdTokenFilter googleIdTokenFilter;

	@Autowired
	private GoogleAuthenticationEntryPoint googleAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/google/login").permitAll().anyRequest().authenticated()
				.and().exceptionHandling()
				.authenticationEntryPoint((AuthenticationEntryPoint) googleAuthenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and().oauth2Login()
				.userInfoEndpoint().oidcUserService(customOidcUserService).and()
				.successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							org.springframework.security.core.Authentication authentication)
							throws IOException, ServletException {
						System.out.println("SUCCESS===========>" + authentication.getName());
					}
				}).failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
							AuthenticationException exception) throws IOException, ServletException {
						System.out.println("ERROR===========>" + exception.toString());
						System.out.println("ERROR===========>" + response.getStatus());
					}
				});
		http.addFilterBefore(googleIdTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
