package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ApproveResponseDTO;
import com.example.demo.dto.RefundResponseDTO;
import com.example.demo.repository.RefundRepository;
import com.example.demo.repository.model.Refund;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefundService {
	
	@Autowired
	public RefundRepository refundRepository;
	
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
	public com.example.demo.dto.RefundResponseDTO readyRefund(Refund refund) {
		Map<String,String> parameters = new HashMap<>();
		parameters.put("cid", "TC0ONETIME");
		parameters.put("tid", "T6e0e5aa69e83c956d12");
		parameters.put("cancel_amount", "3000");
		parameters.put("cancel_tax_free_amount", "0");
		parameters.put("cancel_vat_amount", "0");
		parameters.put("cancel_available_amount","3000");
		
		HttpEntity<Map<String,String>> requestEntity = new HttpEntity(parameters,this.getHeaders());
		
		RestTemplate template = new RestTemplate();
		
        String url = "https://open-api.kakaopay.com/online/v1/payment/cancel";
        
        RefundResponseDTO refundResponse = template.postForObject(url, requestEntity, RefundResponseDTO.class);
		
        return refundResponse;
	}
	
	/**
	 * 헤더 생성
	 * @return
	 */
	private org.springframework.http.HttpHeaders getHeaders(){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Authorization", "SECRET_KEY DEVA5A12588C57F001FADBB1F09F61A652DBADF2");
		headers.set("Content-type", "application/json");
        
        return headers;
	}
	
	public List<Refund> refundFindById(Integer userId) {
		List<Refund> refundList = new ArrayList<>();
		refundList = refundRepository.refundFindById(userId);
		return refundList;
	}

}
