package com.example.demo.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.dto.TestMatch;
import com.example.demo.dto.TestUser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MatchHandler extends TextWebSocketHandler{
	
	private final Map<String, TestMatch> MBTIS = new ConcurrentHashMap<>();
	private final Map<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("매치로 들어옴!!!!!!!!!!!!!!!!!!!!!!!");
		TestUser user = (TestUser)session.getAttributes().get("principal");
		TestMatch test = TestMatch.builder().mbti(user.getMbti()).uploadFileName(user.getUploadFileName())
				.nickname(user.getNickname()).build();
		MBTIS.put(session.getId(), test);
		CLIENTS.put(session.getId(), session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		CLIENTS.remove(session.getId());
		MBTIS.remove(session.getId());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		TestUser user = (TestUser)session.getAttributes().get("principal");
		TestMatch test = TestMatch.builder().mbti(user.getMbti()).uploadFileName(user.getUploadFileName())
				.nickname(user.getNickname()).build();
		ObjectMapper objectMapper = new ObjectMapper();
		for(String key : MBTIS.keySet()) {
			System.out.println("내가 보낸 mbti!!! :" +  message.getPayload());
			System.out.println("찾은 mbti!!! : " + MBTIS.get(key).getMbti());
			if(Integer.parseInt(message.getPayload()) == MBTIS.get(key).getMbti()) {
				String MyDTO = objectMapper.writeValueAsString(test);
				System.out.println("매칭 성공!!!!!!!!!!!!!!");
				String userDTO = objectMapper.writeValueAsString(MBTIS.get(key));
				CLIENTS.get(key).sendMessage(new TextMessage(MyDTO)); // 내 userDTO를 상대에게 전송
				session.sendMessage(new TextMessage(userDTO)); // 상대의 userDTO를 나에게 전송
				break;
			}
		}
	}
}
