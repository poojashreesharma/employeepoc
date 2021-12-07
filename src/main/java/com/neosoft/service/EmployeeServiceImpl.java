package com.neosoft.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.dto.EmployeeDto;
import com.neosoft.entity.Employee;
import com.neosoft.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository ;

	@Override
	public Iterable<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void register(Employee emp) {
		employeeRepository.save(emp);
		
	}

	@Override
	public String updateEmployee(int id , EmployeeDto empDto) {
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
			return "Employee with " + id + "doesnot exist.";
		
		employeeRepository.save(employee);
		return "Employee updated successfully";
	}
	
	@Override
	public List<Employee> sortByDateOfBirth (){
		List<Employee> empList = StreamSupport.stream(employeeRepository.findAll().spliterator(),
				false).sorted(Comparator.comparing(Employee::getDateOfBirth)).collect(Collectors.toList());
		return empList;
	}
	
	@Override
	public List<Employee> sortByDateOfJoining (){
		List<Employee> empList = StreamSupport.stream(employeeRepository.findAll().spliterator(),
				false).sorted(Comparator.comparing(Employee::getDateOfJoining)).collect(Collectors.toList());
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
			return "Employee with id " + id + " removed from database." ; 
		}else
			return "Employee with id "+id+ " not found";
	}

}
