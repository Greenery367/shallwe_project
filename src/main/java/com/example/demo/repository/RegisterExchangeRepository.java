package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegisterExchangeRepository {
	
	public int registerExchange( @Param("userId") Integer userId,
								@Param("submallId") Integer submallId,
								@Param("amount") Long amount);
	
}
