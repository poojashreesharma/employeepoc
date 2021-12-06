package com.neosoft.service;

import java.util.List;

import com.neosoft.dto.EmployeeDto;
import com.neosoft.entity.Employee;

public interface EmployeeService {
	
	public Iterable<Employee> getAllEmployees();
	
	public void register(Employee emp);
	
	public String updateEmployee(int id , EmployeeDto empDto);
	
	public List<Employee> sortByDateOfBirth();
	
	public List<Employee> sortByDateOfJoining();
	
	public List<Employee> searchEmployeeByFirstName(String firstName);
	
	public List<Employee> searchEmployeeByLastName(String lastName);
	
	public List<Employee> searchEmployeeByPinCode(int pinCode);
	
	public void deleteUser (int id);
	
	
}
