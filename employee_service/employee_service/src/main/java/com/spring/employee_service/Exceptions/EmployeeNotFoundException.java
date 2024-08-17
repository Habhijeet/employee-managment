package com.spring.employee_service.Exceptions;

public class EmployeeNotFoundException  extends Exception{
	private String message;

	public EmployeeNotFoundException(String message) {
		super(message);
		
	}
	

}
