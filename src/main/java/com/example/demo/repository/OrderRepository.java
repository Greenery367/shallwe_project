package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.repository.model.Order;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderRepository {
	
	public void createdNewOrder(@Param("orderId")String orderId,
								@Param("userId")Integer userId, 
								@Param("name")String name, 
								@Param("cashAmount")Integer cashAmount, 
								@Param("platform")Integer platform);
	
	public void updateOrderStatus(String orderId);
	public Order checkOrder(@Param("orderId")String orderId,@Param("totalAmount")Integer totalAmount);
}
