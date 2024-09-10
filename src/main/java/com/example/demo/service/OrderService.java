package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.model.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	@Autowired
	public OrderRepository orderRepository;
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	
	// 가주문 생성
	@Transactional
	public String makeNewOrder(Integer userId, Integer cashAmount, Integer platform) {
		String name = cashAmount+"원 캐쉬 충전";
		
		UUID uuid = UUID.randomUUID();
		String orderId = (String)(userId+"_"+uuid);
		System.out.println(orderId);
		System.out.println(orderId.getClass());
		orderRepository.createdNewOrder(orderId, userId, name, cashAmount, platform);
		
		return orderId;
	}
	
	
	// 주문 상태 변경
	@Transactional
	public void changeOrderStatus(String orderId) {
		orderRepository.updateOrderStatus(orderId);
		return;
	}
	
	// 주문 정보 확인
	public Order checkOrder(String orderId,Integer totalAmount) {
		Order checkResult = orderRepository.checkOrder(orderId,totalAmount);
		return checkResult;
	}
	
	

}
