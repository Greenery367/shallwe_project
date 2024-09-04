package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.model.Lecture;
import com.example.demo.service.LectureService;


@Controller
@RequestMapping("/lecture")
public class LectureController {

	private final LectureService lectureService;
	
	@Autowired
	public LectureController(LectureService lectureService) {
		this.lectureService = lectureService;
	}
	
	@GetMapping("/category/{categoryId}")
	public String getlectureByCategory(@PathVariable("categoryId") Integer categoryId, Model model) {
		List<Lecture> lectureList;
		
		lectureList = lectureService.getLectureByCategory(categoryId);
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("categoryId", categoryId);
		
		return "lecture/list";
	}
	
	
}
