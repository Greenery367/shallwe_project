package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.repository.model.CashHistory;

@Mapper
public interface CashHistoryRepository {
	
	// 모든 캐쉬 히스토리 조회
	public List<CashHistory> selectAllCashHistory();

	// id로 캐쉬 히스토리 조회
	public List<CashHistory> selectCashHistoryById(Integer userId);
	
	// 환불 신청 내역만 조회 (미환불, 환불완료 모두 포함)
	public List<CashHistory> selectCashHistoryAllRefund();
}
