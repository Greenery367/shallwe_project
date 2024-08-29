package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.CompatibilityListDTO;
import com.example.demo.dto.TestUser;
import com.example.demo.service.MatchService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/chat")
public class ChatTest {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private MatchService matchService;
	
	@GetMapping("/room")
	public String roomPage(HttpServletRequest request) {
		TestUser user = (TestUser)session.getAttribute("principal");
		return "chat/chatRoom";
	}
	
	@GetMapping("/list")
	public String listPage() {
		return "chat/chatList";
	}
	
	@GetMapping("/sign-in")
	public String signInPage() {
		session.invalidate();
		return "chat/signIn";
	}
	
	@PostMapping("/sign-in")
	public String postMethodName(TestUser user) {
		user = matchService.createUser(user);
		if(user.getUploadFileName() == null) {
			String name = "694399bb-cb96-4e8c-ac4c-056a12427d7d_winter3.jpeg";
			user.setUploadFileName(name);
		}
		session.setAttribute("principal", user);
		return "chat/chatRoom";
	}
	
	@GetMapping("/match")
	public String matchPage(Model model) {
		TestUser user = (TestUser)session.getAttribute("principal");
		int mbtiId = matchService.getMbtiIdByUserId(user.getId());
		List<CompatibilityListDTO> compatibility = matchService.getCompatibilityList(mbtiId);
		model.addAttribute("compatibility",compatibility);
		return "match/matchSystem";
	}
	
}
