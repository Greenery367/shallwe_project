package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.User;


@Mapper
public interface UserRepository {
	
	public int insert(User user);
	public User findById(@Param("id")String id);
}
