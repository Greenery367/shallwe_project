package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.CashRefundDTO;
import com.example.demo.dto.RefundDTO;
import com.example.demo.repository.model.Refund;


@Mapper
public interface RefundRepository {
	// 캐쉬 환불 신청
	public void insertRefundRequest(CashRefundDTO cashRefundDTO);
	// 캐쉬 환불 버튼 활성화 체크 
    public int checkRefundRequest(@Param("orderId")int orderId, @Param("userId")int userId);
	
	
	// 모든 환불 정보 가져오기
	public List<Refund> selectAllRefund(@Param("limit") Integer limit,
										@Param("offset")Integer offset);
	

	public List<Refund> refundFindById (@Param("userId") Integer userId);

	// 모든 환불 정보+주문 플랫폼 가져오기
	public List<RefundDTO> selectAllRefundDto(@Param("limit") Integer limit,
										@Param("offset")Integer offset);

	// 환불 상태 수정
	public void updateRefundStatus(int id);
	
	// id로 환불 내역 조회하기
	public Refund selectRefundById(int refundId);
	
}
