package com.neosoft.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails ErrorDetails = new ErrorDetails(new Date(),"Validation failed",ex.getBindingResult().toString());
		return new ResponseEntity<>(ErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(EmployeeNotFoundException ex, WebRequest request){
		ErrorDetails ErrorDetails = new ErrorDetails(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(ErrorDetails, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
		ErrorDetails ErrorDetails = new ErrorDetails(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(ErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
