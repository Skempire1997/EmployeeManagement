package com.qsp.springboot_employee_management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.springboot_employee_management.dto.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	Employee findEmployeeByPhone(long phone);
	Employee findEmployeeByEmail(String email);
	Employee getEmployeeByEmail(String email);
	@Query("SELECT e  FROM Employee e WHERE e.address=?1")
	List<Employee> empByAddress(String address);
	
	List<Employee>findEmployeeBySalaryLessThan(double salary);
	List<Employee>findEmployeeBySalaryGreaterThan(double salary);
//	@Query("SELECT e FROM Employee e WHERE e.grade between  AND ?1")
	List<Employee>findEmployeeByGradeGreaterThan(char grade);
	List<Employee>findEmployeeByGradeLessThan(char grade);
//	@Query("SELECT e FROM Employee e WHERE e.salary between ?1 AND ?2")
	@Query("SELECT e FROM Employee e WHERE e.salary>?1 AND e.salary<?2")
	List<Employee>salBetween(double salary1,double salary2);
//	@Query("SELECT e FROM Employee e WHERE e.name=?1")
	List<Employee>findEmployeeByName(String name);

}
