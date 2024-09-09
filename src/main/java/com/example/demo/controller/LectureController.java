package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.model.Lecture;
import com.example.demo.repository.model.Review;
import com.example.demo.repository.model.User;
import com.example.demo.service.LectureService;
import com.example.demo.service.ReviewService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/lecture")
public class LectureController {

	private final LectureService lectureService;
	private final ReviewService reviewService;
	private final HttpSession httpSession;
	
	@Autowired
	public LectureController(LectureService lectureService, ReviewService reviewService, HttpSession httpSession) {
		this.lectureService = lectureService;
		this.reviewService = reviewService;
		this.httpSession = httpSession;
	}
	
	// 카테고리별 강의 조회
	@GetMapping("/category/{categoryId}")
	public String getlectureByCategory(@PathVariable("categoryId") Integer categoryId, Model model) {
		List<Lecture> lectureList;
		
		lectureList = lectureService.getLectureByCategory(categoryId);
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("categoryId", categoryId);
		
		return "lecture/list";
	}
	
	// 강의 상세보기 
	@GetMapping("/lectureDetail/{id}")
	public String lectureDetail(@PathVariable("id") Integer id, Model model) {
		Lecture lecture = lectureService.readLectureDetail(id);
		List<Review> reviews = reviewService.getReviewByClassId(id);
		User user = (User)httpSession.getAttribute("principal");
		
		model.addAttribute("user", user);
		model.addAttribute("reviews", reviews);
		model.addAttribute("lecture", lecture);
		
		
		return "lecture/lectureDetail";
	}
	
	// 강의 리뷰 등록
	@PostMapping("/createReview")
	public String createReview(Review review, Model model) {
		User user = (User)httpSession.getAttribute("principal");
		int id = user.getUserId();
		review.setAuthor_id(id);
		reviewService.createReview(review);
		return "redirect:/lecture/lectureDetail/" + review.getClassId();
	}
	
	
}
