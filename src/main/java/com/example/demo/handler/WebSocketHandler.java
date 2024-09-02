package com.example.demo.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.dto.MessageDTO;
import com.example.demo.dto.TestUser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebSocketHandler extends TextWebSocketHandler{

	private final Map<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<>();
	
	@Override // 웹 소켓 연결시 
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("채팅으로 들어옴!!!!!!!!!!!!!!!!!!!!!!!!");
		 TestUser user  = (TestUser)session.getAttributes().get("principal");
		 ObjectMapper objectMapper = new ObjectMapper();
		 String message = " 님이 들어오셧습니다.";
		MessageDTO massageDTO = MessageDTO.builder().name(user.getNickname()).uploadFileName(user.getUploadFileName())
				.message(message).build();
		System.out.println(user.getUploadFileName() + "기본 프사!~!~!!~!");
		// 방에 들어온 사용자 세션 추가
		CLIENTS.put(session.getId(), session);
		for(WebSocketSession s : CLIENTS.values()) {
			if(s.getId() != session.getId()) {
				s.sendMessage(new TextMessage(objectMapper.writeValueAsBytes(massageDTO)));
			}
		}
	}
	
	@Override // 웹 소켓 연결 종료시
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		TestUser user  = (TestUser)session.getAttributes().get("principal");
		 ObjectMapper objectMapper = new ObjectMapper();
		 String message = " 님이 나가셧습니다.";
		MessageDTO massageDTO = MessageDTO.builder().name(user.getNickname()).uploadFileName(user.getUploadFileName())
				.message(message).build();
		// 방에서 나간 사용자 세션 제거
		CLIENTS.remove(session.getId());
		for(WebSocketSession s : CLIENTS.values()) {
			s.sendMessage(new TextMessage(objectMapper.writeValueAsBytes(massageDTO)));
		}
	}
	
	@Override // 데이터 통신시
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		TestUser user  = (TestUser)session.getAttributes().get("principal");
		ObjectMapper objectMapper = new ObjectMapper();
		String userMessage = message.getPayload();
		MessageDTO massageDTO = MessageDTO.builder().name(user.getNickname()).uploadFileName(user.getUploadFileName())
				.message(userMessage).build();
		for (WebSocketSession s : CLIENTS.values()) {
				if(s.getId() != session.getId()) {
				s.sendMessage(new TextMessage(objectMapper.writeValueAsString(massageDTO)));
				}
		} 
	}
}
