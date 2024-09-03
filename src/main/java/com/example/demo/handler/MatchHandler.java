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
	private final Map<String, WebSocketSession> MATCHED = new ConcurrentHashMap<>();
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		TestUser user = (TestUser)session.getAttributes().get("principal");
		CLIENTS.remove(session.getId());
		MBTIS.remove(session.getId());
		// 만약 매치가 성사되고 나갔을 경우 상대에게 나갔음을 알림
		if(MATCHED.get(user.getNickname()) != null) {
			MATCHED.get(user.getNickname()).sendMessage(new TextMessage("quit"));
			MATCHED.remove(user.getNickname());
		}
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		TestUser user = (TestUser)session.getAttributes().get("principal");
		TestMatch test = TestMatch.builder().mbti(user.getMbti()).uploadFileName(user.getUploadFileName())
				.nickname(user.getNickname()).build();
		MBTIS.put(session.getId(), test);
		CLIENTS.put(session.getId(), session);
		if(message.getPayload().equals("accept")) {
			MATCHED.get(user.getNickname()).sendMessage(new TextMessage("accept"));
		} else if(message.getPayload().equals("refuse")) {
			MATCHED.get(user.getNickname()).sendMessage(new TextMessage("refuse"));
		} else if(message.getPayload().equals("stop")) {
			CLIENTS.remove(session.getId());
			MBTIS.remove(session.getId());
		} else {
		ObjectMapper objectMapper = new ObjectMapper();
		for(String key : MBTIS.keySet()) {
			if(Integer.parseInt(message.getPayload()) == MBTIS.get(key).getMbti()) {
				String MyDTO = objectMapper.writeValueAsString(test);
				String userDTO = objectMapper.writeValueAsString(MBTIS.get(key));
				CLIENTS.get(key).sendMessage(new TextMessage(MyDTO)); // 내 userDTO를 상대에게 전송
				session.sendMessage(new TextMessage(userDTO)); // 상대의 userDTO를 나에게 전송
				MATCHED.put(MBTIS.get(key).getNickname(), session); // 수락과 거절 사이의 MATCHED에 상대 추가
				MATCHED.put(user.getNickname(), CLIENTS.get(key)); // 내 정보도 MATCHED에 추가
				CLIENTS.remove(session.getId()); // 매칭 성공시 매칭하는 유저 정보 삭제 
				MBTIS.remove(session.getId()); // 위와 같음
				CLIENTS.remove(key);
				MBTIS.remove(key);
				break;
			}
		}
		}
	}
}
