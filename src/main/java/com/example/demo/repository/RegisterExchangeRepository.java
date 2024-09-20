package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.RegisterExchangeDTO;

@Mapper
public interface RegisterExchangeRepository {
	
	// 환전 신청
	public int registerExchange( @Param("userId") Integer userId,
								@Param("submallId") Integer submallId,
								@Param("amount") Long amount);
	
	// 환전 내역
	public List<RegisterExchangeDTO> getExchangeHistory (@Param("userId")Integer userId);
	
	
}
