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
	
	
	// DI - 생성자 의존 주입 
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
	public String makeNewOrder(Integer userId, Long cashAmount, Integer platform, String orderId) {
		String name = cashAmount+"원 캐쉬 충전"; // 주문명 생성
		if(platform == 1) {
			UUID uuid = UUID.randomUUID(); // 랜덤값 생성
			orderId = (String)(userId+"_"+uuid); // 주문 고유번호 생성
		} 	
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
	 * 결제 요청 후 - 가주문 내역 확인 (tid)
	 * @param orderId
	 * @param totalAmount
	 * @return
	 */
	public OrderDetail checkOrder(String tid) {
		OrderDetail newOrderDetail = orderDetailRepository.selectOrderDetailByTid(tid);
		return newOrderDetail;
	}
	
	/**
	 * 결제 요청 후 - 가주문 내역 확인 (paymentKey-orderId)
	 * @param orderId
	 * @param totalAmount
	 * @return
	 */
	public OrderDetail checkOrderByOrderId(String orderId) {
		OrderDetail newOrderDetail = orderDetailRepository.selectOrderDetailByOrderId(orderId);
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
	
	/**
	 * 진주문 삭제
	 * @param orderId
	 * @param tid
	 * @param userId
	 */
	@Transactional
	public void deleteFailedOrder(String orderId, String tid, int userId) {
		orderRepository.deleteOrder(orderId, tid, userId);
		orderDetailRepository.deleteOrderDetail(orderId, userId);
	}

	/**
	 * user_id와 amount로 현재 캐쉬 정보 수정
	 * @param userId
	 * @param amount
	 */
	public void updateUsersCurrentCash(int userId, Long amount) {
		orderRepository.updateCurrentCash(userId,amount);
		return;
	}
	

}
