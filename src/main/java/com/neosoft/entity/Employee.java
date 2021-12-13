package com.neosoft.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	
	@Column
	@Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
	private @Getter @Setter int empID;
	
	@Column(name="FIRST_NAME")
	@NotNull(message = "First Name cannot be empty")
	@Length(min=3,max=20)
	private @Getter @Setter String firstName ;
	
	@NotNull(message = "Last Name cannot be empty")
	@Column(name="LAST_NAME")
	@Length(min=3,max=20)
	private @Getter @Setter String lastName;
	
	@Column(name="DEPARTMENT")
	private @Getter @Setter String department;
	
	@Column(name="DESIGNATION")
	private @Getter @Setter String designation;
	
	@Column(name="SALARY")
	@Digits(fraction = 2, integer = 6)
	private @Getter @Setter float salary;
	
	@Column(name="DATE_OF_BIRTH")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Past
	private @Getter @Setter Date dateOfBirth;
	
	@Column(name="DATE_OF_JOINING")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Past
	private @Getter @Setter Date dateOfJoining;
	
	@Column(name="EMAIL")
	@Email
	private @Getter @Setter String email;
	
	@Column(name="MOBILE_NO")
	@Digits(fraction=0, integer=10)
	private @Getter @Setter String mobileNo;
	
	@Column(name="CITY")
	@Length(min=3,max=20)
	private @Getter @Setter String city;
	
	@Column(name="PINCODE")
	@Range(min = 100000,max=999999)
	private @Getter @Setter int pincode;
	
	private @Getter @Setter boolean deleted = Boolean.FALSE;

}
