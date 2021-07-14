package com.finalproject.HRM.configs;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.finalproject.HRM.entities.CustomOAuth2User;
import com.finalproject.HRM.implementations.CustomOAuth2UserService;
import com.finalproject.HRM.implementations.CustomOidcUserService;
import com.finalproject.HRM.implementations.UserService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomOAuth2UserService oauthUserService;

	@Autowired
	private UserService userService;

	@Autowired
	private OAuth2LoginSucessHandler oAuth2LoginSuccessHandler;

	@Autowired
	private CustomOidcUserService customOidcUserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/","/login","/oauth/**").permitAll()
//		.and().oauth2Login().defaultSuccessUrl("/loginSuccess").loginPage("/login").userInfoEndpoint()
//		.userService(oauthUserService);
//			
//			
		http.authorizeRequests().antMatchers("/custom_login", "/oauth/**").permitAll().and().oauth2Login()
				.userInfoEndpoint().oidcUserService(customOidcUserService).and().defaultSuccessUrl("/loginSuccess");
	}
}
