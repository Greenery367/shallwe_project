package com.example.demo.handler;

import java.io.FileOutputStream;
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
	private final Map<WebSocketSession, Integer> KEYS = new ConcurrentHashMap<>();
	
	@Override // 웹 소켓 연결시 
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		 TestUser user  = (TestUser)session.getAttributes().get("principal");
		 Integer key = (Integer)session.getAttributes().get("key");
		 ObjectMapper objectMapper = new ObjectMapper();
		 String message = " 님이 들어오셧습니다.";
		MessageDTO messageDTO = MessageDTO.builder().name(user.getNickname()).uploadFileName(user.getUploadFileName())
				.message(message).build();
		// 방에 들어온 사용자 세션 추가
		CLIENTS.put(user.getNickname(), session);
		KEYS.put(session, key);
		for(WebSocketSession users : KEYS.keySet()) {
			if(KEYS.get(users) == key && users != session) {
				users.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageDTO)));
			}
		}
	}
	
	@Override // 웹 소켓 연결 종료시
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		TestUser user  = (TestUser)session.getAttributes().get("principal");
		Integer key = (Integer)session.getAttributes().get("key");
		 ObjectMapper objectMapper = new ObjectMapper();
		 String message = " 님이 나가셧습니다.";
		MessageDTO messageDTO = MessageDTO.builder().name(user.getNickname()).uploadFileName(user.getUploadFileName())
				.message(message).build();
		// 방에서 나간 사용자 세션 제거
		CLIENTS.remove(user.getNickname());
		for(WebSocketSession users : KEYS.keySet()) {
			if(KEYS.get(users) == key && users != session) {
				users.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageDTO)));
			}
		}
		KEYS.remove(session);
	}
	
	@Override // 데이터 통신시
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		TestUser user  = (TestUser)session.getAttributes().get("principal");
		Integer key = (Integer)session.getAttributes().get("key");
		ObjectMapper objectMapper = new ObjectMapper();
		String userMessage = message.getPayload();
		MessageDTO messageDTO = MessageDTO.builder().name(user.getNickname()).uploadFileName(user.getUploadFileName())
				.message(userMessage).build();
		for(WebSocketSession users : KEYS.keySet()) {
			if(KEYS.get(users) == key && users != session) {
				users.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageDTO)));
			}
		}
	}
	
	// 파일를 저장하는 함수  
	private void saveFile(String id, String message) {
	// 메시지 내용
	String msg = id + " : " + message + "\n";    
	// 파일을 저장한다.
	try (FileOutputStream stream = new FileOutputStream("d:\\chat\\chat", true)) {
		stream.write(msg.getBytes("UTF-8"));    
		} catch (Throwable e) {      e.printStackTrace();    }  
	}
}
