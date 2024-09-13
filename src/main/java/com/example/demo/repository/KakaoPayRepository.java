package com.example.demo.repository;

import java.net.http.HttpHeaders;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.ApproveResponseDTO;
import com.example.demo.dto.ReadyResponseDTO;

// 카카오 페이 - 레포지토리
@Mapper
public interface KakaoPayRepository {

		public ReadyResponseDTO payReady(String name, int totalPrice);
		
	    public ApproveResponseDTO payApprove(String tid, String pgToken);
	    
	    public HttpHeaders getHeaders();
	
	
}
