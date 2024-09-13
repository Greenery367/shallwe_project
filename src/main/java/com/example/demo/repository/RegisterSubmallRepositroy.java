package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegisterSubmallRepositroy {
	
	public int registerSubmall(@Param("userId") Integer userId,
							  @Param("bankId") Integer bankId);
	
	// 신청 유무 판단 메서드
    public int checkSubmallExists(@Param("userId") Integer userId);
		
}
