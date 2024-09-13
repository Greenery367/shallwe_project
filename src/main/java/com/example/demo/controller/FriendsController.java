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
	
	// 친구 검색
	@GetMapping("/findUser")
	public String getMethodName() {
		return "friend/findFriend";
	}
	
	// 검색 결과
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
	
	// 내 친구목록 페이지
	@GetMapping("/all")
	public String allFriendPage(HttpServletRequest request) {
		User user = (User)session.getAttribute("principal");
		int id = user.getUserId();
		List<User>onlineFriends = friendService.checkOnlineFriend(id);
		List<User>offlineFriends = friendService.checkOfflineFriend(id);
		request.setAttribute("onlineList", onlineFriends);
		request.setAttribute("offlineList", offlineFriends);
		return "friend/myFriendList";
	}

	// 대기중인 초대 페이지
	@GetMapping("/wait")
	public String waitFriendPage(HttpServletRequest request) {
		User user = (User)session.getAttribute("principal");
		int id = user.getUserId();
		List<User>waitFriends = friendService.checkWaitFriendList(id);
		List<User>sendFriends = friendService.checkSendFriendList(id);
		request.setAttribute("waitList", waitFriends);
		request.setAttribute("sendList", sendFriends);
		return "friend/waitFriendList";
	}
	
	// 친구 요청 보내기
	@PostMapping("/sendFriend")
	public void postMethodName(@RequestParam(name="userId")int user,
			@RequestParam(name="friendId")int friend) {
		friendService.insertWaitingFriend(user, friend);
	}
	
	// 친구 요청 수락
	@GetMapping("/accept/{id}")
	public void acceptFriend(@PathVariable("id")int id) {
		User user = (User)session.getAttribute("principal");
		int userId = user.getUserId();
		int result =  friendService.checkWaitFriend(id, userId);
		if(result != 0) {
			friendService.addFriend(userId, id);
		} else {
			// 추후 fetch 실패 추가
		}
	}
	
	// 친구 요청 거절
	@GetMapping("refuse/{id}")
	public void refuseFriend(@PathVariable("id")int id) {
		User user = (User)session.getAttribute("principal");
		int userId = user.getUserId();
		friendService.removeWaitFriend(id, userId);
	}
	
	// 친구 요청 취소
	@GetMapping("cancel/{id}")
	public void cancelFriend(@PathVariable("id")int id) {
		User user = (User)session.getAttribute("principal");
		int userId = user.getUserId();
		friendService.removeWaitFriend(userId, id);
	}
	
	// 친구 삭제
	@GetMapping("remove/{id}")
	public void removeFriend(@PathVariable("id")int id) {
		User user = (User)session.getAttribute("principal");
		int userId = user.getUserId();
		friendService.removeFriend(userId, id);
	}
	
}
