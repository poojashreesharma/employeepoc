package com.neosoft.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	
	List<Employee> findByFirstName(String firstName);
	
	List<Employee> findByLastName(String lastName);
	
	List<Employee> findByPincode(int pincode);
	
}
