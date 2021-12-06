package com.neosoft.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	@RequestMapping("/hello")
	public String helloWorld() {
		return "Hello Pooja";
	}

}
