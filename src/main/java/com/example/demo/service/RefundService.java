package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ApproveResponseDTO;
import com.example.demo.dto.RefundResponseDTO;
import com.example.demo.repository.RefundRepository;
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
	
	// 모든 환불 신청 내역 조회
	public List<Refund> getAllRefund(Integer limit, Integer offset) {
		List<Refund> refundList = refundRepository.selectAllRefund(limit, offset);
		return refundList;
	}

	// 카카오 서버 요청
	public com.example.demo.dto.RefundResponseDTO readyRefund(Refund refund) {
		Map<String,String> parameters = new HashMap<>();
		System.out.println("로깅1");
		parameters.put("cid", "TC0ONETIME");
		parameters.put("tid", "T1234567890123456789");
		parameters.put("cancel_amount", "2200");
		parameters.put("cancel_tax_free_amount", "2200");
		parameters.put("cancel_vat_amount", "2200");
		parameters.put("cancel_available_amount","2200");
		System.out.println("로깅2");
		
		HttpEntity<Map<String,String>> requestEntity = new HttpEntity(parameters,this.getHeaders());
		
		RestTemplate template = new RestTemplate();
		
        String url = "https://open-api.kakaopay.com/online/v1/payment/cancel";
        
        RefundResponseDTO refundResponse = template.postForObject(url, requestEntity, RefundResponseDTO.class);
		
        return refundResponse;
	}
	
	private org.springframework.http.HttpHeaders getHeaders(){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Authorization", "SECRET_KEY DEVA5A12588C57F001FADBB1F09F61A652DBADF2");
		headers.set("Content-type", "application/json");
        
        return headers;
	}

}
