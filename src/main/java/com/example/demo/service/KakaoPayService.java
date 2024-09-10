package com.example.demo.service;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ApproveResponseDTO;
import com.example.demo.dto.ReadyResponseDTO;
import com.example.demo.repository.KakaoPayRepository;

@Service
public class KakaoPayService {
	
	private KakaoPayRepository kakaoPayRepository;
	
	public KakaoPayService(KakaoPayRepository kakaoPayRepository) {
		this.kakaoPayRepository=kakaoPayRepository;
	}
	
	public ReadyResponseDTO payReady(String name, int totalPrice) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("cid", "TC0ONETIME");
		parameters.put("partner_order_id", "partner_order_id");
		parameters.put("partner_user_id", "partner_order_id");
		parameters.put("item_name", "캐쉬 충전");
		parameters.put("quantity", "1");
		parameters.put("total_amount", String.valueOf(totalPrice));
		parameters.put("tax_free_amount", "0");
		parameters.put("approval_url", "http://localhost:8080/test/main");
		parameters.put("cancel_url", "http://localhost:8080/cash/charge");
		parameters.put("fail_url", "http://localhost:8080/cash/charge");
		
		
		HttpEntity<Map<String,String>> requestEntity = new HttpEntity(parameters,this.getHeaders());
		
		RestTemplate template = new RestTemplate();
		
		String url = "https://open-api.kakaopay.com/online/v1/payment/ready";
		
		ResponseEntity<ReadyResponseDTO> responseEntity = template.postForEntity(url, requestEntity, ReadyResponseDTO.class);
		
		return responseEntity.getBody();
	}
	
    // 카카오페이 결제 승인
    // 사용자가 결제 수단을 선택하고 비밀번호를 입력해 결제 인증을 완료한 뒤,
    // 최종적으로 결제 완료 처리를 하는 단계
    public ApproveResponseDTO payApprove(String tid, String pgToken) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("cid", "TC0ONETIME");              // 가맹점 코드(테스트용)
        parameters.put("tid", tid);                       // 결제 고유번호
        parameters.put("partner_order_id", "1234567890"); // 주문번호
        parameters.put("partner_user_id", "roommake");    // 회원 아이디
        parameters.put("pg_token", pgToken);              // 결제승인 요청을 인증하는 토큰

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/approve";
        ApproveResponseDTO approveResponse = template.postForObject(url, requestEntity, ApproveResponseDTO.class);
       
        return approveResponse;
    }

	
	private org.springframework.http.HttpHeaders getHeaders(){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Authorization", "SECRET_KEY DEVA5A12588C57F001FADBB1F09F61A652DBADF2");
		headers.set("Content-type", "application/json");
        
        return headers;
	}

}
