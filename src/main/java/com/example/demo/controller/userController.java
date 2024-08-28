package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	/**
	 * 메인 페이지 이동
	 * @return
	 */
	// http://localhost:8080/user/main
	@GetMapping("/main")
	public String mainPage() {
		return "mainPage";
	}
	
	// http://localhost:8080/user/start-test
		@GetMapping("/start-test")
		public String testPage() {
			return "startTestPage";
		}
}
