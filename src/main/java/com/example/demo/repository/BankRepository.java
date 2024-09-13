package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.BankDTO;
import com.example.demo.dto.BankInfoDTO;

@Mapper
public interface BankRepository {
	
	    public void createAccount(@Param("userId") int userId, 
	    		@Param("bankId") String bankId,
	    		@Param("accountNumber") String accountNumber);

	    public void updateAccount(@Param("userId") int userId, 
	    		@Param("bankId") String bankId, 
	    		@Param("accountNumber") String accountNumber);

	    public List<BankInfoDTO> selectAllBanks();	
	    
	    public boolean checkAccountExists(@Param("userId") int userId, 
	    								@Param("bankId") String bankId);

	    
	 // 특정 사용자와 은행 ID의 계좌 정보 조회
	    BankDTO bankAccountFindByUser(@Param("userId") int userId);
	
}
