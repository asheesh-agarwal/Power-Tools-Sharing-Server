package com.asheesh.cs5356.pts.exception;

public class UserAuthenticationFailedException extends Exception {

	private static final long serialVersionUID = -1094132757186550741L;

	public UserAuthenticationFailedException() {
		super();
	}

	public UserAuthenticationFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserAuthenticationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAuthenticationFailedException(String message) {
		super(message);
	}

	public UserAuthenticationFailedException(Throwable cause) {
		super(cause);
	}
}
