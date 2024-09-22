package com.qsp.springboot_employee_management.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.springboot_employee_management.dto.Employee;
import com.qsp.springboot_employee_management.repo.EmployeeRepo;

@Repository
public class EmployeeDao {
	@Autowired
	private EmployeeRepo repo;

	public Employee saveEmployee(Employee employee) {
		return repo.save(employee);

	}

	public Employee findEmployee(int id) {

//		to avoid no suchelementException
		Optional<Employee> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();

		} else {
			return null;

		}
	}

	public List<Employee> findAllEmp() {
		return repo.findAll();
	}

	public Employee deleteEmployee(Employee employee) {
//		Optional<Employee> optional = repo.findById(id);
//		if (optional.isEmpty()) {
//			return null;
//		} else {
////			repo.deleteById(id);
////			return optional.get();
//			Employee employee = optional.get();
//			repo.delete(employee);
//			return employee;
//
//		}
		repo.delete(employee);
		return employee;

	}


	public List<Employee> saveall(List<Employee> employees) {
		return repo.saveAll(employees);
	}

	public Employee findByPhone(long phone) {

		return repo.findEmployeeByPhone(phone);
	}

	public Employee findByEmail(String email) {
//		return repo.findEmployeeByEmail(email);
		return repo.getEmployeeByEmail(email);
	}

	public List<Employee> findByAddress(String address) {
		return repo.empByAddress(address);
	}

	public List<Employee> salLessThan(double salary) {
		return repo.findEmployeeBySalaryLessThan(salary);

	}

	public List<Employee> salGreaterThan(double salary) {
		return repo.findEmployeeBySalaryGreaterThan(salary);

	}

	public List<Employee> salBetween(double salary1, double salary2) {
		return repo.salBetween(salary1, salary2);
	}
	public List<Employee> findByName(String name) {
		return repo.findEmployeeByName(name);
	}
	public List<Employee> gradeGreaterThan(char grade){
		return repo.findEmployeeByGradeLessThan(grade);
	}
	
	
	
//-------------------------------------Not in use------------------------------------
//	public Employee updateEmp(int id, Employee employee) {
////		Optional<Employee> optional = repo.findById(id);
////		if (optional.isPresent()) {
//////			Employee employee=optional.get();
////			employee.setId(id);
////			return repo.save(employee);
////
////		} else {
////			return null;
////
////		}
//
//	}
	
//
//	public Employee updatePhone(int id, long phone) {
//		Optional<Employee> optional = repo.findById(id);
//		if (optional.isPresent()) {
//			Employee employee = optional.get();
//			employee.setPhone(phone);
//			return repo.save(employee);
//		} else {
//			return null;
//
//		}
//
//	}
//
//	public Employee updateEmail(int id, String email) {
//		Optional<Employee> optional = repo.findById(id);
//		if (optional.isEmpty()) {
//			return null;
//		} else {
//			Employee employee = optional.get();
//			employee.setEmail(email);
//			return repo.save(employee);
//
//		}
//	}

//	public Employee updateSalary(int id, double salary) {
//		Optional<Employee> optional = repo.findById(id);
//		if (optional.isEmpty()) {
//			return null;
//		} else {
//			Employee employee = optional.get();
//			employee.setSalary(salary);
//			return repo.save(employee);
//
//		}
//	}

}
