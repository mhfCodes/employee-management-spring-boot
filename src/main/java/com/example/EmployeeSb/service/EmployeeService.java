package com.example.EmployeeSb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.EmployeeSb.exception.RecourseNotFoundException;
import com.example.EmployeeSb.model.Employee;
import com.example.EmployeeSb.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public Employee registerEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public ResponseEntity<Employee> getEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RecourseNotFoundException("Employee With Id" + id + " Does Not Exist"));
		return ResponseEntity.ok(employee);
	}
	
	public ResponseEntity<Employee> updateEmployee(Long id, Employee employeeDetails) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RecourseNotFoundException("Employee With Id" + id + " Does Not Exist"));
		
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RecourseNotFoundException("Employee With Id" + id + " Does Not Exist"));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
