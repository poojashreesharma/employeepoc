package com.neosoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.dto.EmployeeDto;
import com.neosoft.entity.Employee;
import com.neosoft.repository.EmployeeRepository;
import com.neosoft.service.EmployeeService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * Register new employee by providing the below details
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param department
	 * @param designation
	 * @param salary
	 * @param dob
	 * @param city
	 * @param doj
	 * @param pincode
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST , value="/add")
	public @ResponseBody Employee addEmployee(@RequestParam String firstName,@RequestParam String lastName, @RequestParam String email, @RequestParam String department ,
			@RequestParam String designation ,@RequestParam float salary , @RequestParam String dob, @RequestParam String city,
			@RequestParam String doj , @RequestParam long pincode , @RequestParam String mobileNo) {
		Employee em = new Employee();
		em.setFirstName(firstName);
		em.setLastName(lastName);
		em.setDesignation(designation);
		em.setDateOfBirth(dob);
		em.setDepartment(department);
		em.setSalary(salary);
		em.setEmail(email);
		em.setCity(city);
		em.setDateOfJoining(doj);
		em.setPincode(pincode);
		em.setMobileNo(mobileNo);
		employeeService.register(em);
		return em;
		
	}
	
	/**
	 * Returns list of all employees
	 * @return
	 */
	 @RequestMapping(method = RequestMethod.GET, value="/allemployee")
	 @ResponseBody
	 public Iterable<Employee> getAllStudents() {
		return employeeService.getAllEmployees();
	 }
	 
	 /**
	  * Search for the user with the given id and update the user
	  */
	 @RequestMapping(method=RequestMethod.PUT , value="/update/{id}")
	 public String updateUser(@PathVariable int id , @RequestBody EmployeeDto empDto) {
		return employeeService.updateEmployee(id , empDto);
	 }
	 
	 /**
	  * Search employee either by firstname, lastname of pin code
	  */
	 
	 
	 
}
