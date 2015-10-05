package com.asheesh.cs5356.pts.response;

public class Response {

	public static enum Status {
		SUCCESS("0"),

		ERROR("-1");

		private String status;

		Status(String status) {
			this.status = status;
		}

		public String getStatus() {
			return this.status;
		}
	};

	public Response() {
	}

	private Status status = Status.SUCCESS;

	private String errorMessage;

	public Response createErrorResponse(String message) {
		this.status = Status.ERROR;
		this.errorMessage = message;

		return this;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
