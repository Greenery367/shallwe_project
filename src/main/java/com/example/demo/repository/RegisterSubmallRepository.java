package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.RegisterSubmall;
import com.example.demo.repository.model.Submall;

@Mapper
public interface RegisterSubmallRepository {
	
	// 서브몰 등록 신청
	public int registerSubmall(@Param("userId") Integer userId,
							  @Param("bankId") Integer bankId);
	
	// 신청 유무 판단 메서드
    public int checkSubmallExists(@Param("userId") Integer userId);
	
    // 서브몰 id 유무 판단 메서드
    public Submall submallFindById(@Param("userId") Integer userId);
}
