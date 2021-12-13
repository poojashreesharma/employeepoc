package com.neosoft.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.neosoft.entity.Employee;
import com.neosoft.repository.EmployeeRepository;




@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	

	@Test
	void addEmployeeTest() throws Exception {
		Employee employee = new Employee();
		employee.setFirstName("Poojashree");
		employee.setLastName("Sharma");
		employee.setDepartment("Java");
		employee.setDesignation("Developer");
		employee.setSalary(1000);
		employee.setPincode(401303);
		employee.setMobileNo("999999999");
		employee.setEmail("abc@xyz.com");
		employee.setDateOfBirth(new Date());
		employee.setDateOfJoining(new Date());
		employee.setCity("Mumbai");
		employee.setDeleted(false);
		Employee save = employeeRepository.save(employee);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		String resultAll = ow.writeValueAsString(employeeRepository.findAll());
		System.out.println("Add Employee Test : " + resultAll);

		assertNotNull(save);
		employeeRepository.deleteAll();

	}

	@Test
	void getAllEmployeeTest() throws Exception {

		Employee employee1 = new Employee();
		employee1.setFirstName("Poojashree");
		employee1.setLastName("Sharma");
		employee1.setDepartment("Java");
		employee1.setDesignation("Developer");
		employee1.setSalary(1000);
		employee1.setPincode(401303);
		employee1.setMobileNo("999999999");
		employee1.setEmail("abc@xyz.com");
		employee1.setDateOfBirth(new Date());
		employee1.setDateOfJoining(new Date());
		employee1.setCity("Mumbai");
		employee1.setDeleted(false);
		employeeRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setFirstName("Sandeep");
		employee2.setLastName("Sharma");
		employee2.setDepartment("YCCP");
		employee2.setDesignation("Professor");
		employee2.setSalary(1000);
		employee2.setPincode(401303);
		employee2.setMobileNo("999999999");
		employee2.setEmail("abc@xyz.com");
		employee2.setDateOfBirth(new Date());
		employee2.setDateOfJoining(new Date());
		employee2.setCity("Mumbai");
		employee2.setDeleted(false);
		employeeRepository.save(employee2);

		List<Employee> findAll = employeeRepository.findAll();
		assertEquals(2, findAll.size());

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String resultAll = ow.writeValueAsString(employeeRepository.findAll());
		System.out.println("Get all employee :" + resultAll);
		
		employeeRepository.deleteAll();

	}

	@Test
	void searchEmployeeTest() throws Exception {

		Employee employee = new Employee();
		employee.setFirstName("Poojashree");
		employee.setLastName("Sharma");
		employee.setDepartment("Java");
		employee.setDesignation("Developer");
		employee.setSalary(1000);
		employee.setPincode(401303);
		employee.setMobileNo("999999999");
		employee.setEmail("abc@xyz.com");
		employee.setDateOfBirth(new Date());
		employee.setDateOfJoining(new Date());
		employee.setCity("Mumbai");
		employee.setDeleted(false);
		Employee newEmp = employeeRepository.save(employee);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		List<Employee> empList = employeeRepository.findAll();
		String result = ow.writeValueAsString(empList);
		assertNotNull(result);

		System.out.println("userObjectSearchFunctionTest() : \n" + result);


		List<Employee> findByFirstName = employeeRepository.findByFirstName("Poojashree");
		String resultByName = ow.writeValueAsString(findByFirstName);
		assertNotNull(resultByName);

		List<Employee> findByLastName = employeeRepository.findByFirstName("Sharma");
		String resultBylastName = ow.writeValueAsString(findByLastName);
		assertNotNull(resultBylastName);

		List<Employee> findByPincode = employeeRepository.findByPincode(401303);
		String resultByPincode = ow.writeValueAsString(findByPincode);
		assertNotNull(resultByPincode);
		
		employeeRepository.deleteAll();
	}

	@Test
	void deleteEmployeeTest() throws Exception {

		Employee employee1 = new Employee();
		employee1.setFirstName("Poojashree");
		employee1.setLastName("Sharma");
		employee1.setDepartment("Java");
		employee1.setDesignation("Developer");
		employee1.setSalary(1000);
		employee1.setPincode(401303);
		employee1.setMobileNo("999999999");
		employee1.setEmail("abc@xyz.com");
		employee1.setDateOfBirth(new Date());
		employee1.setDateOfJoining(new Date());
		employee1.setCity("Mumbai");
		employee1.setDeleted(false);
		Employee emp = employeeRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setFirstName("Sandeep");
		employee2.setLastName("Sharma");
		employee2.setDepartment("Java");
		employee2.setDesignation("Developer");
		employee2.setSalary(1000);
		employee2.setPincode(401303);
		employee2.setMobileNo("999999999");
		employee2.setEmail("abc@xyz.com");
		employee2.setDateOfBirth(new Date());
		employee2.setDateOfJoining(new Date());
		employee2.setCity("Mumbai");
		employee2.setDeleted(false);
		employeeRepository.save(employee2);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String result = ow.writeValueAsString(employeeRepository.findAll());
		System.out.println("Delete Employee test : " + result);

		employeeRepository.deleteById(emp.getEmpID());
		
		assertThat(employeeRepository.count()).isOne();

	}

}
