package com.example.demo.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.dto.MessageDTO;
import com.example.demo.dto.TestUser;
import com.example.demo.repository.model.Alarm;
import com.example.demo.repository.model.User;
import com.example.demo.service.AlarmService;
import com.example.demo.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Chat1vs1Handler extends TextWebSocketHandler {

	private final Map<WebSocketSession, Integer> CLIENTS = new ConcurrentHashMap<>();
	private final Map<WebSocketSession, Integer> KEYS = new ConcurrentHashMap<>();

	@Autowired
	private ChatService chatService;
	@Autowired
	private AlarmService alarmService;

	@Override // 웹 소켓 연결시
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		User user = (User) session.getAttributes().get("principal");
		Integer key = (Integer) session.getAttributes().get("key");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		List<User> userList = chatService.getUserList(key);
		// 방에 들어온 사용자 세션 추가
		CLIENTS.put(session, user.getUserId());
		KEYS.put(session, key);
		// 대화기록 가져오기
		// d드라이브의 chat 폴더의 chat 파일
		File file = new File("C:\\chatJSON\\roomId_" + key);
		// 파일 있는지 검사
		if (file.exists()) {
			List<MessageDTO> history = objectMapper.readValue(readFile(key), new TypeReference<List<MessageDTO>>() {});
			System.out.println(history);
			for (MessageDTO message : history) {
				// 대화기록 나에게 전송
				System.out.println("전송완료");
				session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
			}
		}
	}

	@Override // 웹 소켓 연결 종료시
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		User user = (User) session.getAttributes().get("principal");
		Integer key = (Integer) session.getAttributes().get("key");
		// 방에서 나간 사용자 세션 제거
		CLIENTS.remove(session);
		KEYS.remove(session);
	}

	@Override // 데이터 통신시
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		User user = (User) session.getAttributes().get("principal");
		Integer key = (Integer) session.getAttributes().get("key");
		ObjectMapper objectMapper = new ObjectMapper();
		String userMessage = message.getPayload();
		MessageDTO messageDTO = MessageDTO.builder().name(user.getNickname()).uploadFileName(user.getUploadFileName())
				.message(userMessage).build();
		boolean view = false; // 상대가 매세지를 봤는지 확인
		for (WebSocketSession users : KEYS.keySet()) {
			if (KEYS.get(users) == key && users != session) {
				users.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageDTO)));
				view = true;
			} 
		}
		if(view == false) {
			// 방에 상대방이 없다면 알림 전송
			// 상대방에게 간 알림이 상태값이 0이면 전송 X
			List<User>userList = chatService.getUserList(key);
			for(User opponent : userList) {
				if(opponent.getUserId() != user.getUserId()) {
					List<Integer>statusList = alarmService.checkStatusAlarm(opponent.getUserId(), user.getUserId());
					if(statusList != null) {
						int read = 0;
						for(int i : statusList) {
							if(i == 1) {
								read++;
							}
						}
						if(read == statusList.size()) {
							// 만약 알림을 전부 읽은 상태라면 새로운 알림을 보냄
							String content = user.getNickname() + " 님이 채팅을 보내셨습니다.";
							Alarm alarm = Alarm.builder().type(1).typeId(key).userId(opponent.getUserId())
									.opponentId(user.getUserId()).content(content).build();
							alarmService.sendAlarm(alarm);
						}
					} else {
						// 만약 보낸  채팅 알람이 하나도 없다면 새로운 알림을 보냄
						String content = user.getNickname() + " 님이 채팅을 보내셨습니다.";
						Alarm alarm = Alarm.builder().type(1).typeId(key).userId(opponent.getUserId())
								.opponentId(user.getUserId()).content(content).build();
						alarmService.sendAlarm(alarm);
					}
				}
			}
		}
		saveFile(user, message.getPayload(), key); // 채팅 로그 저장
		saveLogByJSON(user, userMessage, key); // 채팅 JSON으로 저장
	}

	// 파일를 저장하는 함수
	private void saveFile(User user, String message, int roomId) {
		// 메시지 내용
		String msg = user.getNickname() + ": " + message + "\n";
		// 파일을 저장한다.
		try (FileOutputStream stream = new FileOutputStream("C:\\chatLog\\roomId_" + roomId, true)) {
			stream.write(msg.getBytes("UTF-8"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// 메세지 내용을 JSON형식으로 저장
	private void saveLogByJSON(User user, String message, int roomId) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		// 메세지 내용
		MessageDTO log = MessageDTO.builder().name(user.getNickname()).uploadFileName(user.getUploadFileName())
				.message(message).build();
		String msg = objectMapper.writeValueAsString(log);
		// 파일 저장
		File file = new File("C:\\chatJSON\\roomId_" + roomId);
		if (!file.exists()) {
			try (FileOutputStream writeStream = new FileOutputStream("C:\\chatJSON\\roomId_" + roomId)) {
				writeStream.write(msg.getBytes("UTF-8"));
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} else {
		try (FileInputStream readStream = new FileInputStream(file)) {
				String chatLog = new String(readStream.readAllBytes());
				if(chatLog.startsWith("[")) {
					String edit = chatLog.substring(0, chatLog.length() - 1);
					String newChatLog = edit + ", \n" + msg + "]";
					try (FileOutputStream writeStream = new FileOutputStream("C:\\chatJSON\\roomId_" + roomId)) {
						writeStream.write(newChatLog.getBytes("UTF-8"));
					} catch (Throwable e) {
						e.printStackTrace();
					}
				} else {
					String newChatLog = "[ " + chatLog + ", \n" + msg + "]";
					try (FileOutputStream writeStream = new FileOutputStream("C:\\chatJSON\\roomId_" + roomId)) {
						writeStream.write(newChatLog.getBytes("UTF-8"));
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
		 	catch (Throwable e) {
			e.printStackTrace();
		 }
		}
	}

	// 채팅 내용을 파일로 부터 읽어온다.
	private String readFile(int roomId) {
		// d드라이브의 chat 폴더의 chat 파일
		File file = new File("C:\\chatJSON\\roomId_" + roomId);
		// 파일 있는지 검사
		if (!file.exists()) {
			return "";
		}
		// 파일을 읽어온다.
		try (FileInputStream stream = new FileInputStream(file)) {
			return new String(stream.readAllBytes());
		} catch (Throwable e) {
			e.printStackTrace();
			return "";
		}
	}
}
