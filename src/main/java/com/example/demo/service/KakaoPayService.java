package com.example.demo.service;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ApproveResponseDTO;
import com.example.demo.dto.ReadyResponseDTO;
import com.example.demo.repository.KakaoPayRepository;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.OrderDetail;

@Service
public class KakaoPayService {
	
	@Autowired
	private KakaoPayRepository kakaoPayRepository;
	@Autowired
	private OrderService orderService;
	
	
	@Autowired
	public KakaoPayService(KakaoPayRepository kakaoPayRepository,OrderService orderService) {
		this.kakaoPayRepository=kakaoPayRepository;
		this.orderService = orderService;
	}
	
	@Transactional
	public ReadyResponseDTO payReady(Long totalPrice,String orderId) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("cid", "TC0ONETIME");
		parameters.put("partner_order_id", "partner_order_id");
		parameters.put("partner_user_id", "partner_user_id");
		parameters.put("item_name", totalPrice+"원 캐쉬 충전");
		parameters.put("quantity", "1");
		parameters.put("total_amount", String.valueOf(totalPrice));
		parameters.put("tax_free_amount", "0");
		parameters.put("approval_url", "http://localhost:8080/cash/result/kakao");
		parameters.put("cancel_url", "http://localhost:8080/cash/canceled/kakao");
		parameters.put("fail_url", "http://localhost:8080/cash/failed/kakao");
		
		// 주문 세부사항 만들기
		OrderDetail orderDetail = OrderDetail.builder()
											.orderId(orderId)
											.cid("TC0ONETIME")
											.partnerOrderId("partner_order_id")
											.partnerUserId("partner_user_id")
											.totalAmount(Integer.parseInt(totalPrice.toString()))
											.itemName(totalPrice+"원 캐쉬 충전")
											.taxFreeAmount(0)
											.build();
		orderService.makeNewOrderDetail(orderDetail);
		
		// http 메세지 만들기
		HttpEntity<Map<String,String>> requestEntity = new HttpEntity(parameters,this.getHeaders());
		RestTemplate template = new RestTemplate();
		String url = "https://open-api.kakaopay.com/online/v1/payment/ready";
		
		// http response 값 반환
		ResponseEntity<ReadyResponseDTO> responseEntity = template.postForEntity(url, requestEntity, ReadyResponseDTO.class);
		
		return responseEntity.getBody();
	}
	
	
	/**
	 * 카카오 페이 - 결제 승인
	 * @param tid
	 * @param pgToken
	 * @return
	 */
    public ApproveResponseDTO payApprove(String tid, String pgToken) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("cid", "TC0ONETIME");              // 가맹점 코드(테스트용)
        parameters.put("tid", tid);                       // 결제 고유번호
        parameters.put("partner_order_id", "partner_order_id"); // 주문번호
        parameters.put("partner_user_id", "partner_user_id");    // 회원 아이디
        parameters.put("pg_token", pgToken);              // 결제승인 요청을 인증하는 토큰

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/approve";
        ApproveResponseDTO approveResponse = template.postForObject(url, requestEntity, ApproveResponseDTO.class);
       
 
        return approveResponse;
    }

    
    /**
     * 결제 요청을 위한 헤더 생성
     * @return
     */
	private org.springframework.http.HttpHeaders getHeaders(){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Authorization", "SECRET_KEY DEVA5A12588C57F001FADBB1F09F61A652DBADF2");
		headers.set("Content-type", "application/json");
        
        return headers;
	}

}
