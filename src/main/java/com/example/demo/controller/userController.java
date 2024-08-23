package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class userController {
	
	/**
	 * 메인 페이지 이동
	 * @return
	 */
	@GetMapping("/main")
	public String mainPage() {
		return "mainPage";
	}
}
