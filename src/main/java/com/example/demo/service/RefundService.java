package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ApproveResponseDTO;
import com.example.demo.dto.RefundDTO;
import com.example.demo.dto.RefundResponseDTO;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RefundRepository;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.OrderDetail;
import com.example.demo.repository.model.Refund;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefundService {
	
	@Autowired
	public RefundRepository refundRepository;
	@Autowired
	public OrderRepository orderRepository;
	@Autowired
	public OrderDetailRepository orderDetailRepository;
	
	/**
	 * 모든 환불 정보 객체 가져오기
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<RefundDTO> getAllRefundDto (Integer limit, Integer offset){
		List<RefundDTO> allRefundDtoList = refundRepository.selectAllRefundDto(limit,offset);
		return allRefundDtoList;
	}
	
	/**
	 * id로 환불 내역 조회하기
	 * @param refundId
	 * @return
	 */
	public Refund getRefundById(int refundId) {
		return refundRepository.selectRefundById(refundId);
	}
	
	/**
	 * 카카오페이 - 환불 요청
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Refund> getAllRefund(Integer limit, Integer offset) {
		List<Refund> refundList = refundRepository.selectAllRefund(limit, offset);
		return refundList;
	}

	/**
	 * 카카오 페이 - 환불 처리
	 * @param refund
	 * @return
	 */
	public com.example.demo.dto.RefundResponseDTO readyRefundForKakao(Refund refundDto) {
		
		// order amount 찾기
		Order order = orderRepository.selectOrderById(refundDto.orderId);
		OrderDetail orderDetail = orderDetailRepository.selectOrderDetailByOrderId(order.orderId);
		
		
		Map<String,String> parameters = new HashMap<>();
		parameters.put("cid", "TC0ONETIME");
		parameters.put("tid", orderDetail.tid);
		parameters.put("cancel_amount", order.getAmount().toString());
		parameters.put("cancel_tax_free_amount", "0");
		parameters.put("cancel_vat_amount", "0");
		parameters.put("cancel_available_amount",order.getAmount().toString());
		
		// HttpMessage 만들기
		HttpEntity<Map<String,String>> requestEntity = new HttpEntity(parameters,this.getKakaoHeaders());
		
		RestTemplate template = new RestTemplate();
		
		// 환불 요청 url
        String url = "https://open-api.kakaopay.com/online/v1/payment/cancel";
        
        // 응답 객체 생성
        RefundResponseDTO refundResponse = template.postForObject(url, requestEntity, RefundResponseDTO.class);
        
        return refundResponse;
	}
	
	/**
	 * 카카오 헤더 생성
	 * @return
	 */
	private org.springframework.http.HttpHeaders getKakaoHeaders(){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Authorization", "SECRET_KEY DEVA5A12588C57F001FADBB1F09F61A652DBADF2");
		headers.set("Content-type", "application/json");
        
        return headers;
	}
	
	
	/**
	 * 토스 페이 - 환불 처리
	 * @param refund
	 * @return
	 */
	public List readyRefundToToss(String id) {
		
		
		// order amount 찾기
		Order order = orderRepository.selectOrderById(Integer.parseInt(id));
		OrderDetail orderDetail = orderDetailRepository.selectOrderDetailByOrderId(order.orderId);
		
		Map<String,String> parameters = new HashMap<>();
		parameters.put("paymentKey", order.orderId);
		parameters.put("cancelReason", "고객 변심");
		
		// HttpMessage 만들기
		HttpEntity<Map<String,String>> requestEntity = new HttpEntity(parameters,this.getTossHeaders());
		
		RestTemplate template = new RestTemplate();
		
		// 환불 요청 url
        String url = "https://api.tosspayments.com/v1/payments/5EnNZRJGvaBX7zk2yd8ydw26XvwXkLrx9POLqKQjmAw4b0e1/cancel";
        
        // 응답 객체 생성
        List<String> response = template.postForObject(url, requestEntity, List.class);
        
        return response;
	}

	/**
	 * 토스 헤더 생성
	 * @return
	 */
	private org.springframework.http.HttpHeaders getTossHeaders(){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Authorization", "Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg==");
		headers.set("Content-type", "application/json");
        
        return headers;
	}
	
}
