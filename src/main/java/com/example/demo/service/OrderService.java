package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.OrderDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	@Autowired
	public OrderRepository orderRepository;
	@Autowired
	public OrderDetailRepository orderDetailRepository;
	
	
	// DI
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
		this.orderDetailRepository = orderDetailRepository;
	}
	
	
	/**
	 *  가주문 생성
	 * @param userId
	 * @param cashAmount
	 * @param platform
	 * @return
	 */
	@Transactional
	public String makeNewOrder(Integer userId, Integer cashAmount, Integer platform) {
		String name = cashAmount+"원 캐쉬 충전"; // 주문명 생성
		UUID uuid = UUID.randomUUID(); // 랜덤값 생성
		String orderId = (String)(userId+"_"+uuid); // 주문 고유번호 생성
		orderRepository.createdNewOrder(orderId, userId, name, cashAmount, platform); // DB에 가주문내역 생성
		return orderId;
	}
	
	/**
	 * tid 설정
	 * @param tid
	 */
	@Transactional
	public void setTid(String orderId, String tid) {
		// 주문번호로 주문 찾아 tid update
		orderDetailRepository.updateTid(orderId,tid);
		return;
	}
	
	/**
	 * 주문 요청시 주문 세부내역 생성
	 * @param orderDetail
	 */
	@Transactional
	public void makeNewOrderDetail(OrderDetail orderDetail) {
		orderDetailRepository.createNewOrderDetail(orderDetail.orderId, 
													orderDetail.cid, 
													orderDetail.partnerOrderId, 
													orderDetail.partnerUserId, 
													orderDetail.itemName, 
													orderDetail.totalAmount, 
													orderDetail.taxFreeAmount);	
		System.out.println("흠 세부주문 완료");
		return;
	};
	
	
	/**
	 * 결제 승인 후 - 주문 상태 변경
	 * @param orderId
	 */
	@Transactional
	public void changeOrderStatus(String orderId) {
		orderRepository.updateOrderStatus(orderId);
		return;
	}
	
	/**
	 * 결제 요청 후 - 가주문 내역 확인
	 * @param orderId
	 * @param totalAmount
	 * @return
	 */
	public OrderDetail checkOrder(String tid) {
		OrderDetail newOrderDetail = orderDetailRepository.selectOrderDetailByTid(tid);
		return newOrderDetail;
	}
	
	/**
	 * 결제 요청 후 - 가주문-진주문 내역 비교, 인증
	 * @param tid
	 * @return
	 */
	public Order checkOrderRecord(int userId, OrderDetail orderDetail) {
		Order order = orderRepository.checkOrder(userId ,orderDetail.orderId, orderDetail.totalAmount);
		return order;
	}
	

}
