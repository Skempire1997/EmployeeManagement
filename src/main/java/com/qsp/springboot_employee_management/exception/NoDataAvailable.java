package com.qsp.springboot_employee_management.exception;

public class NoDataAvailable extends RuntimeException {
	private String message;

	public NoDataAvailable(String message) {
		super();
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}

}
