package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.User;


@Mapper
public interface UserRepository {
	
	public int insert(User user);
	public User findByNickname(@Param("nickname")String nickname);
	public List<User> findLikeNickname(@Param("nickname")String nickname);
	public User findById(@Param("id")String id);
	public User findByEmail(@Param("email")String email);
	public int updatePasswordByEmail(@Param("password")String password, @Param("email")String email);
}
