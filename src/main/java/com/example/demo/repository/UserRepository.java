package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.User;


@Mapper
public interface UserRepository {
	
	public User findByUsername(@Param("username")String username);
}
