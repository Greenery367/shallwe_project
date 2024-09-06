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
	public User findByUserId(@Param("id")int id);
	public int insertWatingFriend(@Param("userId")int userId,@Param("friendId")int friendId);
}
