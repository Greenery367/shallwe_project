package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/cs")
public class CsController {
	
	/*
	 * 홈 화면 -> 고객지원 탭 클릭  
	 */
	@GetMapping("/main")
	public String csMain() {
		return "cs/csMain";
	}
	
	/*
	 * 질문 글 작성 페이지 요청
	 */
	@GetMapping("post")
	public String requestQNA() {
		return "cs/post";
	}
	
	/*
	 * 질문 글 작성 요청
	 */
	@PostMapping("post")
	public String postQNA() {
		
		return "";
	}
	
	
	
	
	
}
