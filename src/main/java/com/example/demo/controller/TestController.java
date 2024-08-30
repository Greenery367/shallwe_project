package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.model.Question;
import com.example.demo.service.QuestionService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/test")
@NoArgsConstructor
public class TestController {
	
	private HttpSession httpSession;
	private QuestionService questionService;
	Integer progressNumber;
	
	 @Autowired
	    public TestController(QuestionService questionService) {
	        this.questionService = questionService;
	        progressNumber=1;
	    }
	
	
	// http://localhost:8080/test/main
	@GetMapping("/main")
	public String goMain() {
		return "mainPage";
	}
	
	// http://localhost:8080/test/start
	@GetMapping("/start")
	public String startTest(org.springframework.ui.Model model) {
		
		int answerNum=1;
		System.out.println("-----------");
		Question question=questionService.selectAnswerById(1);
		
		model.addAttribute("question", question);
		return "startTestPage";
	}
	
	@GetMapping("/check-answer/{id}/{answer}")
	public String getMethodName(@RequestParam String param,
									@PathVariable("id")int id,
									@PathVariable("answer")int answer) {
		System.out.println(id);
		System.out.println(answer);
		
		progressNumber++;
		
		return new String();
	}
	
	

}
