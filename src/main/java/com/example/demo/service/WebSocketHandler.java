package com.example.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler{

	private final Map<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<>();
	
	@Override // 웹 소켓 연결시 
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 방에 들어온 사용자 세션 추가
		CLIENTS.put(session.getId(), session);
		for(WebSocketSession s : CLIENTS.values()) {
			s.sendMessage(new TextMessage(session.getId() + "님이 들어오셧습니다."));
		}
		System.out.println("방에 들어옴 : " + session.getId());
	}
	
	@Override // 웹 소켓 연결 종료시
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		// 방에서 나간 사용자 세션 제거
		CLIENTS.remove(session.getId());
		for(WebSocketSession s : CLIENTS.values()) {
			s.sendMessage(new TextMessage(session.getId() + "님이 나가셨습니다."));
		}
	}
	
	@Override // 데이터 통신시
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	
		String id = session.getId();
		for (WebSocketSession s : CLIENTS.values()) {
			if(s.getId() != id) {
				s.sendMessage(message);
			}
		} 
		System.out.println(message + "들어온 메세지 ---------------------");
	}
}
