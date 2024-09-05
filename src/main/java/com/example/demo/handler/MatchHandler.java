package com.example.demo.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.dto.JoinRoomDTO;
import com.example.demo.dto.TestMatch;
import com.example.demo.dto.TestUser;
import com.example.demo.repository.model.ChatRoom;
import com.example.demo.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MatchHandler extends TextWebSocketHandler{
	
	private final Map<String, TestMatch> MBTIS = new ConcurrentHashMap<>(); // 매칭중인 사람들의 MBTI
	private final Map<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<>(); // 매칭중인 사람들의 세션
	private final Map<String, String> WANTED = new ConcurrentHashMap<>(); // 매칭 원하는 MBTI 정보
	private final Map<TestMatch, WebSocketSession> MATCHED = new ConcurrentHashMap<>(); // 매치된 사람끼리의 정보
	
	@Autowired
	private ChatService chatService;
	private int count = 0; // 채팅방을 만들기위한 카운트
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		TestUser user = (TestUser)session.getAttributes().get("principal");
		TestMatch test = TestMatch.builder().mbti(user.getMbti()).uploadFileName(user.getUploadFileName())
				.nickname(user.getNickname()).id(user.getId()).build();
		CLIENTS.remove(session.getId());
		MBTIS.remove(session.getId());
		// 만약 매치가 성사되고 나갔을 경우 상대에게 나갔음을 알림
		if(MATCHED.get(test) != null) {
			MATCHED.get(test).sendMessage(new TextMessage("quit"));
			MATCHED.remove(test);
		}
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		TestUser user = (TestUser)session.getAttributes().get("principal");
		TestMatch test = TestMatch.builder().mbti(user.getMbti()).uploadFileName(user.getUploadFileName())
				.nickname(user.getNickname()).id(user.getId()).build();
		MBTIS.put(session.getId(), test);
		CLIENTS.put(session.getId(), session);
		WANTED.put(session.getId(), message.getPayload());
		if(message.getPayload().equals("success")) {
			// 매칭된 두사람 모두 수락을 눌렀을경우 방을 생성해주고 방ID를 배포
			String opponentName = null;
			int opponentId = 0;
			for(TestMatch userDTO : MATCHED.keySet()) {
				if(MATCHED.get(userDTO) == session) {
					opponentName = userDTO.getNickname();
					opponentId = userDTO.getId();
					MATCHED.remove(userDTO);
				}
			}
			ChatRoom chatRoom = ChatRoom.builder().name(user.getNickname() + "," + 
			opponentName).headCount(2).build();
			chatService.createChatRoom(chatRoom);
			int roomId = chatService.selectRoomId();
			JoinRoomDTO userJoin = JoinRoomDTO.builder()
				.userId(user.getId()).roomId(roomId).build();
			JoinRoomDTO opponentJoin = JoinRoomDTO.builder()
				.userId(opponentId).roomId(roomId).build();
			chatService.joinChatRoom(userJoin);
			chatService.joinChatRoom(opponentJoin);
			session.sendMessage(new TextMessage("roomId:"+ roomId));
			MATCHED.get(test).sendMessage(new TextMessage("roomId:" + roomId));
			MATCHED.remove(test);
		} else if(message.getPayload().equals("accept")) {
			MATCHED.get(test).sendMessage(new TextMessage("accept"));
		} else if(message.getPayload().equals("refuse")) {
			MATCHED.get(test).sendMessage(new TextMessage("refuse"));
		} else if(message.getPayload().equals("stop")) {
			CLIENTS.remove(session.getId());
			MBTIS.remove(session.getId());
			WANTED.remove(session.getId());
		} else {
		ObjectMapper objectMapper = new ObjectMapper();
		for(String key : MBTIS.keySet()) {
			if(Integer.parseInt(message.getPayload()) == MBTIS.get(key).getMbti() && Integer.parseInt(WANTED.get(key)) == user.getMbti()) {
				System.out.println("상대가 원하는 MBTI : " + Integer.parseInt(WANTED.get(key)));
				System.out.println("내 MBTI : " + user.getMbti());
				System.out.println("내가 원하는 MBTI : " + Integer.parseInt(message.getPayload()));
				System.out.println("상대 MBTI : " + MBTIS.get(key).getMbti());
				String MyDTO = objectMapper.writeValueAsString(test);
				String userDTO = objectMapper.writeValueAsString(MBTIS.get(key));
				CLIENTS.get(key).sendMessage(new TextMessage(MyDTO)); // 내 userDTO를 상대에게 전송
				session.sendMessage(new TextMessage(userDTO)); // 상대의 userDTO를 나에게 전송
				MATCHED.put(MBTIS.get(key), session); // 수락과 거절 사이의 MATCHED에 상대 추가
				MATCHED.put(test, CLIENTS.get(key)); // 내 정보도 MATCHED에 추가
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
