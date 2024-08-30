package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.model.Question;
import com.example.demo.service.QuestionService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
	
	private HttpSession httpSession;
	
	@Autowired
	private QuestionService questionService;
	private int progressNumber;
	
	public TestController(HttpSession httpSession, QuestionService questionService){
		this.httpSession  = httpSession;
		this.questionService = questionService;
		progressNumber=1;
	}
	

	/**
	 * 메인 페이지 이동
	 * @return
	 */
	// http://localhost:8080/test/main
	@GetMapping("/main")
	public String mainPage() {
		return "mainPage";
	}
	
	// http://localhost:8080/test/start-test
	@GetMapping("/start-test")
	public String testPage(Model model) {
		
		List<Question> questionList = questionService.getAllQuestion();
		int progressNumber = 0;
		model.addAttribute("questionList", questionList);
		model.addAttribute("progressNumber", progressNumber);
		return "test/startTestPage";
	}
	
	
}
