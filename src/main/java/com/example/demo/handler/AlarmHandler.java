package com.example.demo.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.dto.AlarmDTO;
import com.example.demo.repository.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AlarmHandler extends TextWebSocketHandler {

	private final Map<Integer, WebSocketSession> CLIENTS = new ConcurrentHashMap<>();

	@Override // 웹 소켓 연결시
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		User user = (User)session.getAttributes().get("principal");
		CLIENTS.put(user.getUserId(), session); // 유저 id를 key로 session 저장
	}
	
	@Override // 웹 소켓 종료시
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		User user = (User)session.getAttributes().get("principal");
		CLIENTS.remove(user.getUserId());
	}
	
	@Override // 알람 보낼때
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		AlarmDTO alarmDTO = objectMapper.readValue(message.getPayload(), AlarmDTO.class);
		String content = objectMapper.writeValueAsString(alarmDTO);
		CLIENTS.get(alarmDTO.getReceiveUserId()).sendMessage(new TextMessage(content)); // 보내는 유저에게 알람 content 전송
	}
}
