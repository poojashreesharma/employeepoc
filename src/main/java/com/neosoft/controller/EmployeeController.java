package com.neosoft.controller;

import java.util.Date;
import java.util.List;

import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@RequestMapping(method=RequestMethod.POST , value="/register")
	@ResponseBody
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {
		
		Employee employee = employeeService.register(emp);
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}
	
	/**
	 * Returns list of all employees
	 * @return
	 */
	 @RequestMapping(method = RequestMethod.GET, value="/getemployees")
	 @ResponseBody
	 public ResponseEntity<List<Employee>> getEmployees() {
		 List<Employee> empList = employeeService.getAllEmployees();
		return new ResponseEntity<>(empList,HttpStatus.FOUND);
	 }
	 
	 /**
	  * Search for the employee with the given id and update the user
	  */
	 @RequestMapping(method=RequestMethod.PUT , value="/update/{id}")
	 public ResponseEntity<Employee> updateUser(@PathVariable int id , @RequestBody EmployeeDto empDto) {
		Employee emp =employeeService.updateEmployee(id , empDto);
		return new ResponseEntity<>(emp,HttpStatus.CREATED);
	 }
	 
	 /**
	  * Search for the employee with the given firstname
	  */
	 @RequestMapping(method=RequestMethod.GET , value="/name")
	 public ResponseEntity<List<Employee>> getEmployeeByFirstName(@RequestParam String name) {
		List<Employee> empList= employeeService.searchEmployeeByFirstName(name);
		return new ResponseEntity<> (empList, HttpStatus.FOUND);
	 }
	 
	 /**
	  * Search for the employee with the given lastname
	  */
	 @RequestMapping(method=RequestMethod.GET , value="/lastname")
	 public ResponseEntity<List<Employee>> getEmployeeByLastName(@RequestParam String lastName) {
		 List<Employee> empList= employeeService.searchEmployeeByLastName(lastName);
		 return new ResponseEntity<> (empList, HttpStatus.FOUND);
		 
	 }
	 
	 /**
	  * Search for the employee with the given pincode
	  */
	 @RequestMapping(method=RequestMethod.GET , value="/pincode")
	 public ResponseEntity<List<Employee>> getEmployeeByPinCode(@RequestParam int pincode) {
		 List<Employee> empList= employeeService.searchEmployeeByPinCode(pincode);
		 return new ResponseEntity<> (empList, HttpStatus.FOUND);
	 }
	 
	 /**
	  * Sort employee by Date of birth /Date of Joining
	  */
	 @RequestMapping(method=RequestMethod.GET, value="/sortby/{sortBy}")
	 public ResponseEntity<List<Employee>> sortby(@PathVariable String sortBy){
		 List<Employee> empList =employeeService.sort(sortBy);
		 return new ResponseEntity<>(empList, HttpStatus.FOUND);
	 }
	 
	 /**
	  * Delete employee by id
	  */
	 @RequestMapping(method=RequestMethod.DELETE, value="/delete/{id}")
	 public ResponseEntity<String> delete(@PathVariable int id) {
		 employeeService.deleteById(id);
		 return new ResponseEntity<>("Employee with id "+ id +" deleted.", HttpStatus.OK);
	 }
	 
	 /**
	  * Soft delete by id
	  */
	 @RequestMapping(method=RequestMethod.PUT, value="/softdelete/{id}")
	 public ResponseEntity<String> softDelete(@PathVariable int id) {
		 String message = employeeService.softDeleteById(id);
		 return new ResponseEntity<>(message, HttpStatus.OK);
	 }
	 
}
