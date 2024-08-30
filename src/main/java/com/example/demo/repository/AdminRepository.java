package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Admin;
import com.example.demo.dto.Advertise;
import com.example.demo.dto.User;

@Mapper
public interface AdminRepository {
	
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
	
	// 광고추가 
	public int insertAdvertise(Advertise advertise);
	// 광고 수정
	public int updateAdvertise(Advertise advertise);
	// 광고 삭제
	public int deleteAdvertiseById(Integer id);
	
	
	
}
