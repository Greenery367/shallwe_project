package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.RegisterExchangeDTO;
import com.example.demo.repository.model.RegisterExchange;


@Mapper
public interface RegisterExchangeRepository {
	
	// 환전 신청
	public int registerExchange( @Param("userId") Integer userId,
								@Param("submallId") Integer submallId,
								@Param("amount") Long amount);
	
	// 서브몰 찾기 
	public Integer submallFindById(@Param("userId") Integer userId);
	
	
	// 환전 내역
	public List<RegisterExchangeDTO> getExchangeHistory (@Param("userId")Integer userId);
	
	
	// 모든 환전 신청 내역
	public List<RegisterExchange> selectAllExchange(@Param("limit") int limit,
													@Param("offset")int offset);
	
	// id로 환전 내역 찾기
	public RegisterExchange selectRegisterExchange(int id);

	// 환전 신청 - 완료 처리하기
	public void updateExchangeStatusToFinished(Integer exchageRecordId);

}
