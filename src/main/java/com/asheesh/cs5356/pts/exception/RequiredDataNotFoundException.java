package com.asheesh.cs5356.pts.exception;

public class RequiredDataNotFoundException extends Exception {

	private static final long serialVersionUID = 4627199607207497809L;

	public RequiredDataNotFoundException() {
		super();
	}

	public RequiredDataNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RequiredDataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequiredDataNotFoundException(String message) {
		super(message);
	}

	public RequiredDataNotFoundException(Throwable cause) {
		super(cause);
	}
}
