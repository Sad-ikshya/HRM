package com.finalproject.HRM.common.config;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.finalproject.HRM.web.user.entities.Role;
import com.finalproject.HRM.web.user.entities.User;
import com.finalproject.HRM.web.user.repositories.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private UserRepository userRepo;
	@Value( "${spring.security.oauth2.client.registration.google.client-id}" )
	private String CLIENT_ID;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
				// Specify the CLIENT_ID of the app that accesses the backend:
				.setAudience(Collections.singletonList(CLIENT_ID))
				// Or, if multiple clients access the backend:
				// .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				.build();

		// Get jwt token and validate
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(header==null || header.isEmpty()) {
			log.error("Token not provided!");
			try {
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		final String token = header.split(" ")[1].trim();
		// (Receive idTokenString by HTTPS POST)
		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(token);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if (idToken != null) {
			Payload payload = idToken.getPayload();
			// Print user identifier
			String userId = payload.getSubject();
			System.out.println("User ID: " + userId);

			// Get profile information from payload
			String email = payload.getEmail();
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");

			/*
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			*/
			// Use or store profile information
			// Get user identity and set it on the spring security context
			Optional<User> useropt = userRepo.findByEmail(email);
			User user = null;
			if (useropt.isEmpty()) {
				//If user is not present on database - save user first
				User userEntity = User.builder().fullName(name).email(email).joinedDate(new Date().getTime()/1000).role(Role.EMPLOYEE)
						.photo(pictureUrl).build();

				user = userRepo.insert(userEntity);
			} else {
				user = useropt.get();
			}

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
					null, user == null ? List.of() : user.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			try {
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 

		} else {
			log.error("Invalid ID-token. (Google refers JWT access-token as ID-token)");
			try {
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}

	}

}
