package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.repository.model.Order;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderRepository {
	
	// 새로운 결제 내역 만들기
	public void createdNewOrder(@Param("orderId")String orderId,
								@Param("userId")Integer userId, 
								@Param("name")String name, 
								@Param("cashAmount")Integer cashAmount, 
								@Param("platform")Integer platform);
	
	// 결제 상태 수정 - 결제 완료
	public void updateOrderStatus(String orderId);
	
	// 결제 내역 확인 (인증)
	public Order checkOrder(@Param("userId")int userId,
							@Param("orderId")String orderId,
							@Param("totalAmount")int totalAmount);
	
	// 결제 내역 조회 - orderId
}
