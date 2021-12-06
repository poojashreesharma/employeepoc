package com.neosoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="employee")
public class Employee {
	
	
	@Column
	@Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
	private @Getter @Setter int empID;
	
	@Column(name="firstname")
	private @Getter @Setter String firstName ;
	
	@Column(name="lastname")
	private @Getter @Setter String lastName;
	
	@Column(name="department")
	private @Getter @Setter String department;
	
	@Column(name="designation")
	private @Getter @Setter String designation;
	
	@Column(name="salary")
	private @Getter @Setter float salary;
	
	@Column(name="DOB")
	private @Getter @Setter String dateOfBirth;
	
	@Column(name="DOJ")
	private @Getter @Setter String dateOfJoining;
	
	@Column(name="email")
	private @Getter @Setter String email;
	
	@Column(name="contact_num")
	private @Getter @Setter String mobileNo;
	
	@Column(name="city")
	private @Getter @Setter String city;
	
	@Column(name="pincode")
	private @Getter @Setter long pincode;

}
