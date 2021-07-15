package com.finalproject.HRM.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.finalproject.HRM.dtos.GoogleUserInfo;
import com.finalproject.HRM.entities.Role;
import com.finalproject.HRM.entities.User;
import com.finalproject.HRM.repositories.UserRepository;

@Service
public class CustomOidcUserService extends OidcUserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		OidcUser oidcUser = super.loadUser(userRequest);

		try {
			return processOidcUser(userRequest, oidcUser);
		} catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

	private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
		GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());

		Optional<User> userOptional = userRepository.getByEmail(googleUserInfo.getEmail());
		if (userOptional.isEmpty()) {
			User user = new User();
			user.setEmail(googleUserInfo.getEmail());
			user.setFirstname(googleUserInfo.getName());
			
			user.setRole(Role.EMPLOYEE);

			userRepository.save(user);
		}
		return oidcUser;
	}

}
