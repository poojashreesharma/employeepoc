package com.neosoft.controller;

import java.util.Date;
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
	public @ResponseBody String addEmployee(@RequestBody Employee emp) {
		
		employeeService.register(emp);
		return "Employee "+emp.getFirstName()+" "+emp.getLastName()+" registered successfully with ID "+ emp.getEmpID();
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
	  * Search for the employee with the given id and update the user
	  */
	 @RequestMapping(method=RequestMethod.PUT , value="/update/{id}")
	 public String updateUser(@PathVariable int id , @RequestBody EmployeeDto empDto) {
		return employeeService.updateEmployee(id , empDto);
	 }
	 
	 /**
	  * Search for the employee with the given firstname
	  */
	 @RequestMapping(method=RequestMethod.GET , value="/name")
	 public List<Employee> getEmployeeByFirstName(@RequestParam String name) {
		return employeeService.searchEmployeeByFirstName(name);
	 }
	 
	 /**
	  * Search for the employee with the given lastname
	  */
	 @RequestMapping(method=RequestMethod.GET , value="/lastname")
	 public List<Employee> getEmployeeByLastName(@RequestParam String lastName) {
		 return employeeService.searchEmployeeByLastName(lastName);
	 }
	 
	 /**
	  * Search for the employee with the given pincode
	  */
	 @RequestMapping(method=RequestMethod.GET , value="/pincode")
	 public List<Employee> getEmployeeByPinCode(@RequestParam int pincode) {
		return employeeService.searchEmployeeByPinCode(pincode);
	 }
	 
	 /**
	  * Sort employee by Date of birth 
	  */
	 @RequestMapping(method=RequestMethod.GET, value="/sortbydateofbirth")
	 public List<Employee> getSortByDateOfBirth(){
		 return employeeService.sortByDateOfBirth();
	 }
	 
	 /**
	  * Sort employee by joining date
	  */
	 @RequestMapping(method=RequestMethod.GET, value="/sortbydateofjoining")
	 public List<Employee> getSortByDateOfJoining(){
		 return employeeService.sortByDateOfJoining();
	 }
	 
	 /**
	  * Delete employee by id
	  */
	 @RequestMapping(method=RequestMethod.DELETE, value="/delete/{id}")
	 public String delete(@PathVariable int id) {
		 return employeeService.deleteById(id);
	 }
	 
	 /**
	  * Soft delete by id
	  */
	 @RequestMapping(method=RequestMethod.PUT, value="/softdelete/{id}")
	 public String softDelete(@PathVariable int id) {
		 return employeeService.softDeleteById(id);
	 }
	 
}
