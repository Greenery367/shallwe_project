package com.example.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.dto.TestUser;

@Component
public class WebSocketHandler extends TextWebSocketHandler{

	private final Map<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<>();
	
	@Override // 웹 소켓 연결시 
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		 TestUser user  = (TestUser)session.getAttributes().get("principal");
		
		// 방에 들어온 사용자 세션 추가
		CLIENTS.put(session.getId(), session);
		for(WebSocketSession s : CLIENTS.values()) {
			s.sendMessage(new TextMessage(user.getNickname() + " 님이 들어오셧습니다."));
		}
	}
	
	@Override // 웹 소켓 연결 종료시
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		TestUser user  = (TestUser)session.getAttributes().get("principal");
		// 방에서 나간 사용자 세션 제거
		CLIENTS.remove(session.getId());
		for(WebSocketSession s : CLIENTS.values()) {
			s.sendMessage(new TextMessage(user.getNickname() + " 님이 나가셨습니다."));
		}
	}
	
	@Override // 데이터 통신시
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		TestUser user  = (TestUser)session.getAttributes().get("principal");
		String id = session.getId();
		System.out.println(message.toString());
		for (WebSocketSession s : CLIENTS.values()) {
				s.sendMessage(message);
		} 
	}
}
