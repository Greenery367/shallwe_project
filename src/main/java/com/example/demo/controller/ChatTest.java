package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.CompatibilityListDTO;
import com.example.demo.dto.JoinRoomDTO;
import com.example.demo.dto.MbtiDTO;
import com.example.demo.dto.TestUser;
import com.example.demo.repository.model.ChatRoom;
import com.example.demo.repository.model.User;
import com.example.demo.service.ChatService;
import com.example.demo.service.FriendService;
import com.example.demo.service.MatchService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/chat")
public class ChatTest {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private MatchService matchService;
	@Autowired
	private UserService userService;
	@Autowired
	private FriendService friendService;
	@Autowired
	private ChatService chatService;
	
	@GetMapping("/room")
	public String roomPage(@RequestParam("roomId")String roomId,Model model) {
		int id = Integer.parseInt(roomId);
		User principal = (User)session.getAttribute("principal");
		List<User>userList = chatService.getUserList(id);
		User opponent = null;
		for(User user : userList) {
			if(user.getUserId() != principal.getUserId()) {
				opponent = user; // 매칭된 상대 정보 담기
			}
		}
		session.setAttribute("key", id);
		model.addAttribute("opponent",opponent);
		model.addAttribute("roomId",roomId);
		return "chat/chatRoom";
	}
	
	@GetMapping("/match")
	public String matchPage(HttpServletRequest request) throws JsonProcessingException {
		User user = (User)session.getAttribute("principal");
		int mbtiId = matchService.getMbtiIdByUserId(user.getUserId());
		user.setMbti(mbtiId);
		session.setAttribute("principal", user);
		MbtiDTO myMbti = matchService.getMbtiNameById(mbtiId);
		List<CompatibilityListDTO> compatibility = matchService.getCompatibilityList(mbtiId);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(compatibility);
		request.setAttribute("compatibilityList",compatibility);
		request.setAttribute("compatibilityJson", json);
		request.setAttribute("mbti", myMbti);
		return "match/matchSystem";
	}
	
	@GetMapping("/profileInfo")
	public String getMethodName(@RequestParam("userId") int id, Model model) {
		User user = friendService.findByUserID(id);
		if(matchService.getMbtiIdByUserId(id) != null) {
			System.out.println("--------------dㅇㅇㅇㅇㅇㅇㅇ------------");
			int mbti =	matchService.getMbtiIdByUserId(id);
			MbtiDTO mbtiDTO = matchService.getMbtiNameById(mbti);
			model.addAttribute("mbti",mbtiDTO);
		}
		model.addAttribute("user",user);
		return "match/userInfo";
	}
	
	@GetMapping("/friendChat")
	public String friendChatPage(@RequestParam("id") int id, Model model) {
		System.out.println("채팅 들어옴!!!!!!!!!!!!!!!!!!!!");
		User principal = (User)session.getAttribute("principal");
		User opponent = userService.searchByUserId(id);
		int userId = principal.getUserId();
		int roomId = 0;
		// 친구와 만든 대화방이 있는지 없는지 검사
		roomId = chatService.checkRoom1vs1(userId, id);
		if(roomId != 0) {
			model.addAttribute("roomId",roomId);
			session.setAttribute("key", roomId);
		} else {
			// 없다면 새로운 대화방을 만듬
			ChatRoom chatRoom = ChatRoom.builder().name(principal.getNickname() + ","
					+ opponent.getNickname()).build();
			chatService.createChatRoom(chatRoom);
			roomId = chatService.selectRoomId();
			JoinRoomDTO userJoin = JoinRoomDTO.builder()
					.userId(userId).roomId(roomId).build();
				JoinRoomDTO opponentJoin = JoinRoomDTO.builder()
					.userId(id).roomId(roomId).build();
			chatService.joinChatRoom(userJoin);
			chatService.joinChatRoom(opponentJoin);
			model.addAttribute("roomId",roomId);
			session.setAttribute("key", roomId);
		}
		model.addAttribute("opponent",opponent);
		model.addAttribute("roomId",roomId);
		return "chat/friendChatRoom";
	}
	
}