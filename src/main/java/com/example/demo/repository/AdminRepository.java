package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.CreateAdvertiseDTO;
import com.example.demo.repository.model.Advertise;

@Mapper
public interface AdminRepository{
	
	// 전체 유저수
	public int countNumberOfUser();
	// 전체유저 충전캐쉬
	public int countChargeAmount();	
	// 전체유저 사용캐쉬
	public int countSpendAmount();
	// 캐쉬 사용률 
	public double countSpendAmountRate();
	
	// 전체 광고 조회
	public List<Advertise> selectAllAdvertise();
	// id로 광고 조회
	public Advertise selectAdvertiseById(@Param("id") Integer id);
	// 현재 게시중인 광고 조회 (status = 1)
	public List<Advertise> selectAdvertiseNow();
	
	
	// 광고추가 
	public int insertAdvertise(CreateAdvertiseDTO dto);
	// 광고 수정
	public int updateAdvertise(Advertise advertise);
	// 광고 삭제
	public int deleteAdvertiseById(Integer id);
	
	// 만료된 광고 중 상태가 1인 광고 조회
	public List<Integer> selectExpiredAdvertise(@Param("now") LocalDateTime now);
	// 광고 상태 업데이트
	public void updateAdvertiseStatus(@Param("status") int status, @Param("id") Integer id);
	
	
}
