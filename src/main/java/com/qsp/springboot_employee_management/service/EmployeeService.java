package com.qsp.springboot_employee_management.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.springboot_employee_management.dao.EmployeeDao;
import com.qsp.springboot_employee_management.dto.Employee;
import com.qsp.springboot_employee_management.exception.EmailNotFound;
import com.qsp.springboot_employee_management.exception.GradeNotPresent;
import com.qsp.springboot_employee_management.exception.IdNotFound;
import com.qsp.springboot_employee_management.exception.NoDataAvailable;
import com.qsp.springboot_employee_management.exception.PhoneNotFound;
import com.qsp.springboot_employee_management.util.ResponseStructure;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao dao;

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {
		double sal = employee.getSalary();
		if (sal < 10000) {
			employee.setGrade('D');
		} else if (sal >= 10000 && sal < 20000) {
			employee.setGrade('C');
		} else if (sal >= 20000 && sal < 40000) {
			employee.setGrade('B');
		} else {
			employee.setGrade('A');
		}
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setMessage("Employee saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveEmployee(employee));
//		return structure;
		return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Employee>> findEmployee(int id) {
		Employee employee = dao.findEmployee(id);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (employee != null) {
			structure.setMessage("Employee Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
//			return structure;
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
		} else {
			throw new IdNotFound("Employee Not Found With Given Id.");
//			structure.setMessage("Employee Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findAllEmp() {
		List<Employee> list = dao.findAllEmp();
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (list.isEmpty()) {

			throw new NoDataAvailable("No Data Available");
//			structure.setMessage("Employees Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(list);
//			return structure;
		} else {
//			throw new NoDataAvailable("No Data Available in Database");
			structure.setMessage("Employee  Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(list);
//			return structure;
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(int id) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		Employee employee = dao.findEmployee(id);
		if (employee != null) {
			dao.deleteEmployee(employee);
			structure.setMessage("Employee  Deleted successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
//			return structure;
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		} else {
			throw new IdNotFound("No Employee found with given id.");
//			structure.setMessage("Employee  Not Found");
//			structure.setStatus(HttpStatus.NO_CONTENT.value());
//			structure.setData(employee);
//			return structure;
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmp(@RequestBody Employee employee, int id) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		Employee employee2 = dao.findEmployee(id);
		if (employee2 != null) {
			employee.setId(id);
			double sal = employee.getSalary();
			if (sal < 10000) {
				employee.setGrade('D');
			} else if (sal >= 10000 && sal < 20000) {
				employee.setGrade('C');
			} else if (sal >= 20000 && sal < 40000) {
				employee.setGrade('B');
			} else {
				employee.setGrade('A');
			}
			dao.saveEmployee(employee);
			structure.setMessage("Employee  Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
//			return structure;

			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		} else {
			throw new IdNotFound("Employee not found with given id");
//			structure.setMessage("Employee  Not Found");
//			structure.setStatus(HttpStatus.NO_CONTENT.value());
//			structure.setData(employee);
//			return structure;
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> saveall(List<Employee> list) {
		ResponseStructure<List<Employee>> structure=new ResponseStructure<List<Employee>>();
		for (Employee employee : list) {
			double sal = employee.getSalary();
			if (sal < 10000) {
				employee.setGrade('D');
			} else if (sal >= 10000 && sal < 20000) {
				employee.setGrade('C');
			} else if (sal >= 20000 && sal < 40000) {
				employee.setGrade('B');
			} else {
				employee.setGrade('A');
			}

		}
		structure.setMessage("Student  saved ");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveall(list));
//		return structure;
		return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Employee>> updatePhone(int id, long phone) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		Employee employee = dao.findEmployee(id);
		if (employee != null) {
			employee.setPhone(phone);
			dao.saveEmployee(employee);
			structure.setMessage("Employee Phone  Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
//			return structure;
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		} else {

			throw new IdNotFound("Employee not found with given id");
//			structure.setMessage("Employee  Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmail(int id, String email) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		Employee employee = dao.findEmployee(id);
		if (employee != null) {
			employee.setEmail(email);
			dao.saveEmployee(employee);
			structure.setMessage("Employee email  Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
//			return structure;
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		} else {

			throw new IdNotFound("Employee not found with given id");
//			structure.setMessage("Employee  Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateSalary(int id, double salary) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		Employee employee = dao.findEmployee(id);
		if (employee != null) {
			employee.setSalary(salary);
			dao.saveEmployee(employee);
			structure.setMessage("Employee salary  Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
//			return structure;
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		} else {

			throw new IdNotFound("Employee not found with given id");
//			structure.setMessage("Employee  Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findByPhone(long phone) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		Employee employee = dao.findByPhone(phone);
		if (employee != null) {
			structure.setMessage("Employee Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
//			return structure;
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
		} else {
			throw new PhoneNotFound("Phone no Not found");
//			structure.setMessage("Employee Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> findByEmail(String email) {

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		Employee employee = dao.findByEmail(email);
		if (employee != null) {
			structure.setMessage("Employee Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
//			return structure;
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
		} else {
			throw new EmailNotFound("Email not found");
//			structure.setMessage("Employee Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;
		}

	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByAddress(String address) {

		List<Employee> employee = dao.findByAddress(address);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (employee.isEmpty()) {

			throw new NoDataAvailable("No Data Available");
//			structure.setMessage("Employee Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;

		} else {
			structure.setMessage("Employee Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
//			return structure;

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salLessThan(double salary) {
		List<Employee> employees = dao.salLessThan(salary);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (employees.isEmpty()) {

			throw new NoDataAvailable("No Data Available");
//			structure.setMessage("Employee Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//			return structure;

		} else {

			structure.setMessage("Employee Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
//			return structure;

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salGreaterThan(double salary) {
		List<Employee> employees = dao.salGreaterThan(salary);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (employees.isEmpty()) {

			throw new NoDataAvailable("No Data Available");
//			structure.setMessage("Employee Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//			return structure;
		} else {

			structure.setMessage("Employee Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
//			return structure;

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salBetween(double salary1, double salary2) {
		List<Employee> employees = dao.salBetween(salary1, salary2);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (employees.isEmpty()) {

			throw new NoDataAvailable("No Data Available");
//			structure.setMessage("Employee Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//			return structure;

		} else {

			structure.setMessage("Employee Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
//			return structure;

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByName(String name) {
		List<Employee> employees = dao.findByName(name);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (employees.isEmpty()) {
			throw new NoDataAvailable("No Employee find with given name");
//			structure.setMessage("Employee Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//			return structure;
		} else {
			structure.setMessage("Employee Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
//			return structure;

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> gradeGreaterThan(char grade) {

		List<Employee> employees = dao.gradeGreaterThan(grade);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (employees.isEmpty()) {
			throw new NoDataAvailable("No Data Available");
//			structure.setMessage("Employee Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//			return structure;
		} else if (grade == 'B' || grade == 'C' || grade == 'D' || grade == 'b' || grade == 'c' || grade == 'd') {
			structure.setMessage("Employee Found successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
//			return structure;

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		} else {
			throw new GradeNotPresent("Grade Not Exist.");
//			structure.setMessage("Employee Not Found successfully");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//			return structure;
		}

	}

}
