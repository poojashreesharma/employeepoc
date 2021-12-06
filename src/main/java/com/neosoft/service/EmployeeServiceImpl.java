package com.neosoft.service;

import java.util.List;
import java.util.Optional;

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
			employee.setCity(empDto.getCity());
			employee.setDepartment(empDto.getDepartment());
			employee.setDesignation(empDto.getDesignation());
			employee.setSalary(empDto.getSalary());
			employee.setPincode(empDto.getPincode());
			employee.setMobileNo(empDto.getMobileNo());
			
		}else
			return "Employee with " + id + "doesnot exist.";
		
		employeeRepository.save(employee);
		return "Employee updated successfully";
	}

}
