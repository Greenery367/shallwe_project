package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.JoinRoomDTO;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.model.ChatRoom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {

	private final ChatRepository chatRepository;
	
	@Transactional
	public void createChatRoom(ChatRoom chatRoom) {
		chatRepository.createRoom(chatRoom);
	}
	
	public void joinChatRoom(JoinRoomDTO joinRoomDTO) {
		chatRepository.joinRoom(joinRoomDTO);
	}
	
	public List<Integer>getUserList(int roomId) {
		List<Integer>userList = null;
		userList = chatRepository.getUserList(roomId);
		return userList;
	}
	
	public int selectRoomId() {
		int id = chatRepository.selectMatchRoom();
		return id;
	}
}
