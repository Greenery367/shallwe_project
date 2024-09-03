package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.ChatRoomDTO;
import com.example.demo.dto.CompatibilityListDTO;
import com.example.demo.dto.MbtiDTO;
import com.example.demo.dto.TestUser;
import com.example.demo.service.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/chat")
public class chatTest {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private MatchService matchService;
	
	@GetMapping("/room")
	public String roomPage(@RequestParam("key")String key) {
		session.setAttribute("chat", key);
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
		MbtiDTO myMbti = matchService.getMbtiNameById(user.getMbti());
		session.setAttribute("principal", user);
		session.setAttribute("mbti", myMbti);
		return "redirect:/chat/match";
	}
	
	@GetMapping("/match")
	public String matchPage(HttpServletRequest request) throws JsonProcessingException {
		TestUser user = (TestUser)session.getAttribute("principal");
		// int mbtiId = matchService.getMbtiIdByUserId(user.getId()); -- 추후 mbti받아오는거로 수정
		List<CompatibilityListDTO> compatibility = matchService.getCompatibilityList(user.getMbti());
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(compatibility);
		request.setAttribute("compatibilityList",compatibility);
		request.setAttribute("compatibilityJson", json);
		return "match/matchSystem";
	}
	
	@GetMapping("/profileInfo")
	public String getMethodName(@RequestParam("name") String name,Model model) {
		model.addAttribute("name",name);
		return "match/userInfo";
	}
	
}
