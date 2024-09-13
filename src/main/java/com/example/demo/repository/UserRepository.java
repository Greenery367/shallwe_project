package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.User;


@Mapper
public interface UserRepository {
	
	public int insert(User user);
	public User findByNickname(@Param("nickname")String nickname);
	public int findLikeNicknameSize(@Param("nickname")String nickname);
	public List<User> findLikeNickname(@Param("nickname")String nickname,@Param("limit")int limit,@Param("offset")int offset);
	public User findById(@Param("id")String id);
	public User findByEmail(@Param("email")String email);
	public int updatePasswordByEmail(@Param("password")String password, @Param("email")String email);

	public long getLectureCash(@Param("userId")Integer userId);

	public User findByUserId(@Param("id")int id);
	public int insertWatingFriend(@Param("userId")int userId,@Param("friendId")int friendId);
	public int addFriendById(@Param("userId")int userId,@Param("friendId")int friendId);
	public int removeFriendById(@Param("userId")int userId,@Param("friendId")int friendId);
	public int removeWaitFriendById(@Param("userId")int userId,@Param("friendId")int friendId);
	public int checkWaitFriend(@Param("userId")int userId,@Param("friendId")int friendId);
	public List<User> checkStatusFriend(@Param("id")int id,@Param("status")int status);
	public List<User> checkWaitFriend(@Param("id")int id);
	public List<User> checkSendFriend(@Param("id")int id);
	// 유저 캐쉬 변경
	public void updateCurrentCash(@Param("userId")int userId, @Param("amount")Long amount);
}

