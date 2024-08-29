package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.TestUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/chat")
public class ChatTest {
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/room")
	public String roomPage(HttpServletRequest request) {
		TestUser user = (TestUser)session.getAttribute("principal");
		return "chat/chatRoom";
	}
	
	@GetMapping("/list")
	public String listPage() {
		return "chat/chatList";
	}
	
	@GetMapping("/signIn")
	public String signInPage() {
		return "chat/signIn";
	}
	
	@PostMapping("/signIn")
	public String postMethodName(TestUser user) {
		session.setAttribute("principal", user);
		return "chat/chatRoom";
	}
	
	@GetMapping("/match")
	public String matchPage() {
		return "match/matchSystem";
	}
	
}
