package com.spring.user_service.response;

public class AuthResponse {
	
	
	private String jwt;

	// constructor
	public AuthResponse(String jwt) {
		this.jwt = jwt;
	}

	// getter
	// return the value of Jwt
	public String getJwt() {
		return jwt;
	}

}
