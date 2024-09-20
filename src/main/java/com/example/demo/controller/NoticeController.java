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
import com.example.demo.repository.model.Notice;
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
		int totalNewsNum = newsList.size();
		Integer pageSize = totalNewsNum/4;
		model.addAttribute("newsList", newsList);
		model.addAttribute("totalNewsNum", totalNewsNum);
		model.addAttribute("pageSize", pageSize);
		return "news/newsPage";
	}
	
	/**
	 * 뉴스 디테일 페이지
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/news-detail/{id}")
	public String newsdetail(@PathVariable("id") int id, Model model) {
		News selectedNews = noticeService.getNewsById(id);
		model.addAttribute("news", selectedNews);
		return "news/newsDetail";
	}

	
	/**
	 * 공지사항 목록 페이지
	 * @return
	 */
	// http://localhost:8080/notice/notice-list
	@GetMapping("/notice-list")
	public String noticePage(Model model) {
		List<Notice> noticeList = noticeService.getAllNotice(0);
		int pageSize = noticeList.size()/5;
		model.addAttribute("noticeList", noticeList);
		return "news/noticePage";
	}
	
	/**
	 * 공지 디테일 페이지
	 * @param noticeId
	 * @param model
	 * @return
	 */
	// http://localhost:8080/notice/notice-detail
		@GetMapping("/notice-detail/{noticeId}")
		public String noticeDetail(@PathVariable("noticeId")int noticeId,
									Model model) {
			Notice notice = noticeService.getNoticeById(noticeId);
			model.addAttribute("notice", notice);
			return "news/noticeDetail";
		}
}
