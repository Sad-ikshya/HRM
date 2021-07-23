package com.finalproject.HRM.common.security.oAuth2;

import java.util.Map;

public class GoogleUserInfo {
	private Map<String, Object> attributes;

	public GoogleUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getId() {
		return (String) attributes.get("sub");
	}

	public String getName() {
		return (String) attributes.get("name");
	}

	public String getEmail() {
		return (String) attributes.get("email");
	}

	public String getPicture()
	{
		return (String) attributes.get("picture");
	}
}
