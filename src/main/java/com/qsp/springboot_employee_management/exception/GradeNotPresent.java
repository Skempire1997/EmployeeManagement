package com.qsp.springboot_employee_management.exception;

public class GradeNotPresent extends RuntimeException {
	private String message;

	public GradeNotPresent(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}
