package com.finalproject.HRM.common.config;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class GoogleIdTokenFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String idTokenString = null;
		
		HttpTransport httpTransport= new HttpTransport();
		JsonFactory

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder( )
				// Specify the CLIENT_ID of the app that accesses the backend:
				.setAudience(Collections.singletonList("ABC"))
				// Or, if multiple clients access the backend:
				// .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				.build();

		// (Receive idTokenString by HTTPS POST)

		final String requestTokenHeader = request.getHeader("Authorization");

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			idTokenString = requestTokenHeader.substring(7);

			GoogleIdToken idToken;
			try {
				idToken = verifier.verify(idTokenString);
				if (idToken != null) {
					Payload payload = idToken.getPayload();

					// Print user identifier
					String userId = payload.getSubject();
					System.out.println("User ID: " + userId);

					UserDetails userDetails = (UserDetails) payload;
					// Get profile information from payload
//				  String email = payload.getEmail();
//				  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//				  String name = (String) payload.get("name");
//				  String pictureUrl = (String) payload.get("picture");
//				  String locale = (String) payload.get("locale");
//				  String familyName = (String) payload.get("family_name");
//				  String givenName = (String) payload.get("given_name");

					// Use or store profile information
					// ...
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			logger.warn("Invalid ID token.");
		}

		filterChain.doFilter(request, response);
	}

}
