package com.neosoft.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.entity.Employee;
import com.neosoft.repository.EmployeeRepository;
import com.neosoft.service.EmployeeService;

@AutoConfigureMockMvc
public class EmployeeControllerMockMvc {
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock 
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;
	
	@Test
    public void registerEmployeeTest() throws Exception {
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


        Employee createdEmp = new Employee(1, "Poojashree", "Sharma","Java","Developer",1000, new Date(), new Date(), "abc@xyz.com","999999999","Mumbai",401303,false);


        Mockito.when(employeeService.register(employee)).thenReturn(createdEmp);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/employee/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(employee));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());

    }
	
}
