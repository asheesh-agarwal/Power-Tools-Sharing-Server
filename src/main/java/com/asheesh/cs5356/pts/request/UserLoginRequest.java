package com.asheesh.cs5356.pts.request;

import javax.validation.constraints.NotNull;

public class UserLoginRequest {

	@NotNull
	private String emailId;
	
	@NotNull
	private String password;
	
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
