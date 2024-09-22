package com.qsp.springboot_employee_management.util;

import java.util.List;

import com.qsp.springboot_employee_management.dto.Employee;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private String message;
	private int status;
	private T data;

}
