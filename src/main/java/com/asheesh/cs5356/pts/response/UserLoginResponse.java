package com.asheesh.cs5356.pts.response;

public class UserLoginResponse extends Response {

	private String userId;

	public UserLoginResponse() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
