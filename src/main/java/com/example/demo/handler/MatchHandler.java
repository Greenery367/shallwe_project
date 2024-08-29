package com.example.demo.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.dto.TestUser;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		ObjectMapper objectMapper = new ObjectMapper();
		for(String key : MBTIS.keySet()) {
			if(message.getPayload().contains(MBTIS.get(key).getMbti())) {
				String MyDTO = objectMapper.writeValueAsString(user);
				String userDTO = objectMapper.writeValueAsString(MBTIS.get(key));
				TextMessage opponent = new TextMessage(userDTO);
				TextMessage myProfile = new TextMessage(MyDTO);
				CLIENTS.get(key).sendMessage(myProfile); // 내 userDTO를 상대에게 전송
				session.sendMessage(opponent); // 상대의 userDTO를 나에게 전송
				break;
			}
		}
	}
}
