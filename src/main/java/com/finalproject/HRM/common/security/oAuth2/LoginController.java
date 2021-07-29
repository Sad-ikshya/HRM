package com.finalproject.HRM.common.security.oAuth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
public class LoginController {
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	@RequestMapping("/custom_login")
	public String customlogin() {
		return "login.html";
	}

	@RequestMapping("/")
	public String home() {
		return "home.html";
	}

	@GetMapping("/loginSuccess")
	public String getLoginInfo(Model model, OAuth2AuthenticationToken authentication) {
		OAuth2AuthorizedClient client = authorizedClientService
				.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
		System.out.println("LOGIN is successful==============");
		return "test.html";
	}

	@GetMapping("/loginFailure")
	public String failureLogin(Model model, OAuth2AuthenticationToken authentication) {
		OAuth2AuthorizedClient client = authorizedClientService
				.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
		System.out.println("LOGIN is unsuccessful==============");
		return "login";
	}
}
