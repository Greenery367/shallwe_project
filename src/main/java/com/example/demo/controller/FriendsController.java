package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.model.User;
import com.example.demo.service.FriendService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendsController {
	
	private final FriendService friendService;
	private final HttpSession session;
	
	@GetMapping("/findUser")
	public String getMethodName() {
		return "friend/findFriend";
	}
	
	@PostMapping("/findUser")
	public String postMethodName(@RequestParam(name="name") String name,
			@RequestParam(name="pageNum")int page , HttpServletRequest request) {
		// 한 페이지에 유저가 10명씩 보이도록 설정
		int limit = 10;
		// 오프셋은 limit * (page - 1)
		int offset = limit * (page - 1);
		List<User>userList = friendService.findLikeUser(name,limit,offset);
		int size = friendService.findLikeUserSize(name);
		int pageNum = (int)Math.ceil(size / limit);
		request.setAttribute("userList", userList); // 검색된 유저리스트
		request.setAttribute("name", name); // 검색어 
		request.setAttribute("current",page); // 현재 페이지
		request.setAttribute("pageSize", pageNum); // 총 페이지 수
		return "friend/findFriend";
	}
	
	@PostMapping("/sendFriend")
	public void postMethodName(@RequestParam(name="userId")int user,
			@RequestParam(name="friendId")int friend) {
		friendService.insertWaitingFriend(user, friend);
	}
	
	@GetMapping("/accept/{id}")
	public void acceptHandler(@PathVariable("id")int id) {
		User user = (User)session.getAttribute("principal");
		
	}
	
	@GetMapping("refuse/{id}")
	public void refuseHandler(@PathVariable("id")int id) {
		
		
	}
	
	@GetMapping("cancel/{id}")
	public void cancelHandler(@PathVariable("id")int id) {
		
		
	}
}
