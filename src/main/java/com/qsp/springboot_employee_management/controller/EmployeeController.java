package com.qsp.springboot_employee_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.qsp.springboot_employee_management.dao.EmployeeDao;
import com.qsp.springboot_employee_management.dto.Employee;
import com.qsp.springboot_employee_management.service.EmployeeService;
import com.qsp.springboot_employee_management.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@Valid @RequestBody Employee employee) {
		return service.saveEmployee(employee);

	}

	@GetMapping("/fetch")
	public ResponseEntity<ResponseStructure<Employee>> findEmployee(@RequestParam int id) {
		return service.findEmployee(id);
	}

	@GetMapping("/fetchall")
	public ResponseEntity<ResponseStructure<List<Employee>>> findAllEmp() {
		return service.findAllEmp();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(@PathVariable int id) {
		return service.deleteEmployee(id);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Employee>> updateEmp(@RequestBody Employee employee, int id) {
		return service.updateEmp(employee, id);

	}

	@PatchMapping("/updatephone")
	public ResponseEntity<ResponseStructure<Employee>> updatePhone(int id, long phone) {
		return service.updatePhone(id, phone);
	}

	@PatchMapping("/updateemail")
	public ResponseEntity<ResponseStructure<Employee>> updateEmail(int id, String email) {
		return service.updateEmail(id, email);
	}

	@PatchMapping("/updatesal")
	public ResponseEntity<ResponseStructure<Employee>> updateSalary(int id, double salary) {
		return service.updateSalary(id, salary);
	}

	@PostMapping("/saveall")
	public ResponseEntity<ResponseStructure<List<Employee>>> saveall(@RequestBody List<Employee> employees) {
		return service.saveall(employees);
	}

	@GetMapping("/fetchbyphone")
	public ResponseEntity<ResponseStructure<Employee>> findByPhone(long phone) {
		return service.findByPhone(phone);
	}
	@GetMapping("/fetchbyemail")
	public ResponseEntity<ResponseStructure<Employee>> findByEmail(String email) {
		return service.findByEmail(email);
	}
	@GetMapping("/fetchbyadd")
	public ResponseEntity<ResponseStructure<List<Employee>>> findByAddress(String address) {
		return service.findByAddress(address);
	}
	@GetMapping("/sallessthan")
	public ResponseEntity<ResponseStructure<List<Employee>>> salLessThan(double salary){
		return service.salLessThan(salary);
	}
	@GetMapping("/salgreaterthan")
	public ResponseEntity<ResponseStructure<List<Employee>>> salGreaterThan(double salary){
		return service.salGreaterThan(salary);
	}
	@GetMapping("/salbetween")
	public ResponseEntity<ResponseStructure<List<Employee>>> salBetween(double salary1,double salary2) {
		return service.salBetween(salary1, salary2);
	}
	@GetMapping("/findbyname")
	public ResponseEntity<ResponseStructure<List<Employee>>> findByName(@RequestParam String name) {
		return service.findByName(name);
	}
	@GetMapping("/gradegreaterthan")
	public ResponseEntity<ResponseStructure<List<Employee>>> gradeGreaterThan(char grade){
		return service.gradeGreaterThan(grade);
	}
	
}
