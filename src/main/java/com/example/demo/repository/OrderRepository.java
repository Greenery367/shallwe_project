package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.repository.model.Order;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderRepository {
	
	// 새로운 결제 내역 만들기
	public void createdNewOrder(@Param("orderId")String orderId,
								@Param("userId")Integer userId, 
								@Param("name")String name, 
								@Param("cashAmount")Long cashAmount, 
								@Param("platform")Integer platform);
	
	// 결제 상태 수정 - 결제 완료
	public void updateOrderStatus(String orderId);
	
	// orderid로 주문 내역 찾기
	public Order selectOrderById(Integer id);
	
	// 결제 내역 확인 (인증)
	public Order checkOrder(@Param("userId")int userId,
							@Param("orderId")String orderId,
							@Param("totalAmount")int totalAmount);
	
	// 결제 삭제
	public void deleteOrder(@Param("orderId")String orderId, @Param("tid")String tid,@Param("userId")int userId);

	
	// 충전 내역
	public List<Order> orderFindById (@Param("userId")int userId); 


	// 유저 현재 캐쉬 수정
	public void updateCurrentCash(@Param("userId")int userId, @Param("amount")Long amount);

}
