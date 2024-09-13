package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.BankDTO;
import com.example.demo.dto.BankInfoDTO;
import com.example.demo.repository.model.RegisterSubmall;
import com.example.demo.repository.model.Submall;

@Mapper
public interface SubmallRepository {
	// 모든 서브몰 내역 확인
	public List<Submall> selectAllSubmall(@Param("limit")int limit, @Param("offset")int offset);
	
	// 모든 서브몰 신청 내역 확인
	public List<RegisterSubmall> selectAllRegisteredSubmall(@Param("limit")Integer limit, @Param("offset")Integer offset);

	
	// 서브몰 생성
	public void addNewSubmall(@Param("submallId")String submallId,
								@Param("userId")int userId,
								@Param("accountId")String accountId,
								@Param("email")String email,
								@Param("phoneNumber")String phoneNumber);
	
	// 서브몰 신청 내역 상태 변경
	public void updateRegisterSubmallStatus(int id);
}
