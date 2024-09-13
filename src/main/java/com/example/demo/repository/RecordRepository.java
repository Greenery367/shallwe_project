package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.RecordDTO;

@Mapper
public interface RecordRepository {

	// 충전 이력
	public List<RecordDTO> ChargeFindById (@Param("userId") Integer userId);

	// 환전 이력
	public List<RecordDTO> ExchangeFindById (@Param("userId") Integer userId);
	
	// 사용 이력 
	public List<RecordDTO> SpendFindById (@Param("userId") Integer userId);
	
	// 환불 이력
	public List<RecordDTO> RefundFindById (@Param("userId") Integer userId);
	
}
