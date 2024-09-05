package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.JoinRoomDTO;
import com.example.demo.repository.model.ChatRoom;

@Mapper
public interface ChatRepository {

	public void createRoom(ChatRoom chatRoom);
	
	public void joinRoom(JoinRoomDTO joinRoom);
	
	public int selectMatchRoom();
}
