package com.neosoft.service;

import java.util.List;

import com.neosoft.dto.EmployeeDto;
import com.neosoft.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	
	public Employee register(Employee emp);
	
	public Employee updateEmployee(int id , EmployeeDto empDto);
	
	public List<Employee> sort(String sortBy);
	
	public List<Employee> searchEmployeeByFirstName(String firstName);
	
	public List<Employee> searchEmployeeByLastName(String lastName);
	
	public List<Employee> searchEmployeeByPinCode(int pinCode);
	
	public String deleteById (int id);
	
	public String softDeleteById (int id);
	
	
}
