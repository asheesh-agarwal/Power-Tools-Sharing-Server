package com.asheesh.cs5356.pts.response;

public class UserRegistrationResponse extends Response {

	private String userId;

	public UserRegistrationResponse() {
		super();
	}

	public UserRegistrationResponse(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
