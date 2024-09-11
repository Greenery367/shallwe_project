package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.OrderDetail;


@Mapper
public interface OrderDetailRepository {
	
	// 주문 세부사항 넣기
	public void createNewOrderDetail(@Param("orderId")String orderid,
										@Param("cid")String cid,
										@Param("partnerOrderId")String partnerOrderId,
										@Param("partnerUserId")String partnerUserId,
										@Param("itemName")String itemName,
										@Param("totalAmount")int totalAmount,
										@Param("taxFreeAmount")int taxFreeAmount);

	// 결제 tid 수정
	public void updateTid(@Param("orderId")String orderId, @Param("tid")String tid); 
	
	// tid로 주문 세부사항 찾기
	public OrderDetail selectOrderDetailByTid(@Param("tid")String tid);
	
}
