package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.model.News;
import com.example.demo.service.AdminService;
import com.example.demo.service.NoticeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private final NoticeService noticeService;
	
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	/**
	 * 뉴스 목록
	 * @param model
	 * @return
	 */
	// http://localhost:8080/notice/news-list
	@GetMapping("/news-list")
	public String newsPage(Model model) {
		List<News> newsList = noticeService.getAllnews();
		model.addAttribute("newsList", newsList);
		System.out.println(newsList);
		return "news/newsPage";
	}
	
	@GetMapping("/news-detail/{id}")
	public String newsdetail(@PathVariable("id") int id, Model model) {
		System.out.println("들어옴!!");
		News selectedNews = noticeService.getNewsById(id);
		model.addAttribute("news", selectedNews);
		return "news/newsDetail";
	}

	
	// http://localhost:8080/notice/notice-list
	@GetMapping("/notice-list")
	public String noticePage() {
		return "news/noticePage";
	}
	
	// http://localhost:8080/notice/notice-list
		@GetMapping("/notice-detail")
		public String noticeDetail() {
			return "news/noticePage";
		}
}
