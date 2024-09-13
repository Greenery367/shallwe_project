package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.JoinRoomDTO;
import com.example.demo.repository.model.ChatRoom;
import com.example.demo.repository.model.User;

@Mapper
public interface ChatRepository {

	public void createRoom(ChatRoom chatRoom);
	
	public void joinRoom(JoinRoomDTO joinRoom);
	
	public int selectMatchRoom();
	
	public List<User>getUserList(int roomId);
	
	public int checkRoom1vs1(@Param("userId_1")int userId_1,@Param("userId_2")int userId_2);
	
}
