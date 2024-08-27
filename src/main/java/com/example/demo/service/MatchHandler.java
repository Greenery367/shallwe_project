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
public class MatchHandler extends TextWebSocketHandler{
	
	private final Map<String, TestUser> MBTIS = new ConcurrentHashMap<>();
	private final Map<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		TestUser user = (TestUser)session.getAttributes().get("principal");
		CLIENTS.put(session.getId(), session);
		MBTIS.put(session.getId(), user);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		CLIENTS.remove(session.getId());
		MBTIS.remove(session.getId());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		TestUser user = (TestUser)session.getAttributes().get("principal");
		for(String key : MBTIS.keySet()) {
			if(message.getPayload().contains(MBTIS.get(key).getMbti())) {
				TextMessage opponent = new TextMessage(MBTIS.get(key).toString());
				TextMessage myProfile = new TextMessage(user.toString());
				CLIENTS.get(key).sendMessage(myProfile);
				session.sendMessage(opponent);
				break;
			}
		}
	}
}
