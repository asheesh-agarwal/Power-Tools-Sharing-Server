package com.asheesh.cs5356.pts.request;

public class UserTerminationRequest {

	private String emailId;

	private String password;

	public UserTerminationRequest() {
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
