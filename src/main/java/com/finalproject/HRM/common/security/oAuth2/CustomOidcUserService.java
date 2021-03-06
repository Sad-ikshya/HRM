package com.finalproject.HRM.common.security.oAuth2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.finalproject.HRM.web.user.dtos.UserDto;
import com.finalproject.HRM.web.user.entities.Role;
import com.finalproject.HRM.web.user.service.UserService;

//@Service
public class CustomOidcUserService extends OidcUserService {
	@Autowired
	private UserService userService;

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

		List<Role> role= new ArrayList<>();
		role.add(Role.EMPLOYEE);
		UserDto userDto = UserDto.builder()
							.fullName(googleUserInfo.getName())
							.email(googleUserInfo.getEmail())
							.roles(role)
							.photo(googleUserInfo.getPicture())
							.build();
//		userService.saveUser(userDto);
		return oidcUser;
	}

}
