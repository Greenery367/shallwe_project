package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.JoinRoomDTO;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.model.ChatRoom;
import com.example.demo.repository.model.User;

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
	
	public List<User>getUserList(int roomId) {
		List<User>userList = null;
		userList = chatRepository.getUserList(roomId);
		return userList;
	}
	
	public int selectRoomId() {
		int id = chatRepository.selectMatchRoom();
		return id;
	}
	
	public int checkRoom1vs1(int userId_1,int userId_2) {
		int id = chatRepository.checkRoom1vs1(userId_1, userId_2);
		return id;
	}
}
