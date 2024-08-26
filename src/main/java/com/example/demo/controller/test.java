package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class test {
	@GetMapping("/test/signIn")
	public String signIn() {
		return "sign/signIn";
	}
	
	@GetMapping("/test/signUp")
	public String signUp() {
		return "sign/signUp";
	}
	
	@GetMapping("/test/id")
	public String getMethodName() {
		return new String();
	}
	
	@GetMapping("/test/idCheck")
	public String abc(@RequestParam(name = "id") String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "sign/idCheck";
	}
	
	
}
