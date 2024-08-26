package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/chat")
public class chatTest {

	@GetMapping("/room")
	public String getMethodName() {
		System.out.println("들어옴 --------------------------");
		return "chat/chatRoom";
	}
	
}
