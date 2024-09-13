package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.BankDTO;
import com.example.demo.dto.RegisterSubmallDTO;
import com.example.demo.repository.model.RegisterSubmall;

@Mapper
public interface RegisterSubmallRepositroy {
	
	public int registerSubmall(@Param("userId") Integer userId,
							  @Param("bankId") Integer bankId);
	

	// 유저 정보를 통해 계좌 찾기
	public BankDTO getBankInfo(int userId);
	
	// 신청 유무 판단 메서드
    public int checkSubmallExists(@Param("userId") Integer userId);
    
    // id로 서브몰 신청 정보 찾기
    public RegisterSubmall selectRegisterSubmallById(int id);
		
}
