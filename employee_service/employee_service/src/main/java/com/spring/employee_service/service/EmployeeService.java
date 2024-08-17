package com.spring.employee_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.employee_service.Exceptions.EmployeeNotFoundException;
import com.spring.employee_service.entity.Employee;
import com.spring.employee_service.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();

	}

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);

	}

	public Employee getEmployeeById(long id) throws EmployeeNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isEmpty()) {
			throw new EmployeeNotFoundException("Employee Not found with given Id:"+ id);

		}
		return employee.get();

	}

	public Employee updateEmply(long id,Employee employee) throws EmployeeNotFoundException {
		Optional<Employee> employe=	employeeRepository.findById(id);
		if (employe.isEmpty()) {
			throw new EmployeeNotFoundException("Employee Not found with given Id:"+ id);

		}
		Employee updateEmployee=employe.get();
		updateEmployee.setName(employee.getName());
		updateEmployee.setDepartment(employee.getDepartment());
		updateEmployee.setPosition(employee.getPosition());
		updateEmployee.setSalary(employee.getSalary());
		
		return employeeRepository.save(updateEmployee);
		
		
	}

	public void  deleteEmployeeById(long id) {
		employeeRepository.deleteById(id);
		
	}

}
