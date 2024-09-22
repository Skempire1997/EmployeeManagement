package com.qsp.springboot_employee_management.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "name must be filled.")
	@NotNull(message = "Name Can not be null")
	private String name;
	@Column(unique = true)
	
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long phone;
	@NotBlank(message = "address must be filled.")
	@NotNull(message = "address Can not be null")
	private String address;
	@Column(unique = true)
	@NotBlank(message = "email must be filled.")
	@NotNull(message = "email Can not be null")
	@Email(regexp = "[a-z0-9._$]+@[a-z]+\\.[a-z]{2,3}",message = "Enter proper Email")
	private String email;
	@Min(value = 1)
	private double salary;
	private char grade;

}
