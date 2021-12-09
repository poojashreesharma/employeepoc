package com.neosoft.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.neosoft.dto.EmployeeDto;
import com.neosoft.entity.Employee;
import com.neosoft.exception.EmployeeNotFoundException;
import com.neosoft.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository ;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee register(Employee emp) {
		return employeeRepository.save(emp);
		
	}

	@Override
	public Employee updateEmployee(int id , EmployeeDto empDto) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
			if (!(empDto.getCity()==null || empDto.getCity().isEmpty()))
				employee.setCity(empDto.getCity());
			if (!(empDto.getDepartment()==null || empDto.getDepartment().isEmpty()))
				employee.setDepartment(empDto.getDepartment());
			if (!(empDto.getDesignation()== null || empDto.getDesignation().isEmpty()))
				employee.setDesignation(empDto.getDesignation());
			if (!(empDto.getSalary()==0))
					employee.setSalary(empDto.getSalary());
			if (!(empDto.getPincode()==0))
				employee.setPincode(empDto.getPincode());
			if (!( empDto.getMobileNo() == null || empDto.getMobileNo().isEmpty()))
				employee.setMobileNo(empDto.getMobileNo());
			
		}else
			throw new EmployeeNotFoundException("Employee with ID "+id+ " doesnot exist");
		
		employeeRepository.save(employee);
		return employee;
	}
	
	@Override
	public List<Employee> sort(String sortBy){
		List<Employee> empList = employeeRepository.findAll((Sort.by(Sort.Direction.ASC, sortBy)));
		return empList;
	}
	
	@Override
	public List<Employee> searchEmployeeByFirstName(String firstName){
		return employeeRepository.findByFirstName(firstName);
	}
	
	@Override
	public List<Employee> searchEmployeeByLastName(String lastName){
		return employeeRepository.findByLastName(lastName);		
	}
	
	@Override
	public List<Employee> searchEmployeeByPinCode(int pincode){
		return employeeRepository.findByPincode(pincode);
		
	}
	
	@Override
	public String deleteById(int id) {
		employeeRepository.deleteById(id);
		return "Employee with id " + id + " removed from database." ;
	}
	
	@Override
	public String softDeleteById(int id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if (emp.isPresent()) {
			emp.get().setDeleted(Boolean.TRUE);
			employeeRepository.save(emp.get());
			return "Employee with id " + id + " disabled" ; 
		}else
			return "Employee with id "+id+ " not found";
	}

}
