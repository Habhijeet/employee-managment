package com.spring.employee_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.employee_service.Exceptions.EmployeeNotFoundException;
import com.spring.employee_service.entity.Employee;
import com.spring.employee_service.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employees") 
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public List<Employee> getAllEmployee() {
		return	employeeService.getAllEmployees();
		 

	}

	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		System.out.println(employee.getName());
		employeeService.addEmployee(employee);
		return employee;

	}

	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable(value = "id") long id) throws EmployeeNotFoundException {
		return employeeService.getEmployeeById(id);

	}
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable (value="id") long id,@RequestBody Employee employee) throws EmployeeNotFoundException {
		return	employeeService.updateEmply(id,employee);
		 
		
	}
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable (value="id") long id) {
			employeeService.deleteEmployeeById(id);
		 
		
	}

}
