package com.neosoft.service;

import com.neosoft.dto.EmployeeDto;
import com.neosoft.entity.Employee;

public interface EmployeeService {
	
	public Iterable<Employee> getAllEmployees();
	
	public void register(Employee emp);
	
	public String updateEmployee(int id , EmployeeDto empDto);
	
	
}
