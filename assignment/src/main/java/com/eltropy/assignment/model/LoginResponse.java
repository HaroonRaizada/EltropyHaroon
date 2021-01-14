package com.eltropy.assignment.model;

public class LoginResponse {
	private final String jwttoken;

	public LoginResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
