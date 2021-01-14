package com.eltropy.assignment.model;

import org.springframework.stereotype.Component;

@Component
public class SignOutModel {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
