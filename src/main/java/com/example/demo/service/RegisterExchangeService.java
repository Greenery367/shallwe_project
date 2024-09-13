package com.example.demo.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterExchangeDTO;
import com.example.demo.repository.RegisterExchangeRepository;
import com.example.demo.repository.model.RegisterExchange;

@Service
public class RegisterExchangeService {
	private final RegisterExchangeRepository registerExchangeRepository;
	
	@Autowired
	public RegisterExchangeService(RegisterExchangeRepository registerExchangeRepository) {
		this.registerExchangeRepository = registerExchangeRepository; 
	}
	
	// 모든 환전 내역 
	public List<RegisterExchange> getAllExchangeList(int limit, int offset){
		return registerExchangeRepository.selectAllExchange(limit,offset);
	} 
	
	// id로 환전 내역 찾기
	public RegisterExchange getRegisterExchange(int id) {
		return registerExchangeRepository.selectRegisterExchange(id);
	}
	
	// 환전 내역 만들기
	public void registerExchange(RegisterExchangeDTO registerExchangeDTO) {
		registerExchangeRepository.registerExchange(registerExchangeDTO.getUserId(), registerExchangeDTO.getSubmallId(), registerExchangeDTO.getAmount());
	}

	// 환전 (지급 대행) 요청 보내기
	public String sendRequestExchange(RegisterExchange exchageRecord) {
		// 환전 신청 금액 조회
		// int count = registerExchangeRepository.
	       
	       // 1. 환전 신청 금액 조회 - 유저 현재 잔액 조회
	       
	       // 2. 현재 사이트가 지급할 수 있는 잔액 체크
		   //  System.out.println("잔액 확인!!!!!!!!!!!!"+a);
		
		   // 3. 만약 잔액이 충분하다면, 지급대행 요청
	       
	       
	       return null;
	}
	

   private HttpResponse checkShallWeBalance() throws IOException, InterruptedException {
   	HttpRequest request = HttpRequest.newBuilder()
   		    .uri(URI.create("https://api.tosspayments.com/v1/payouts/sub-malls/settlements/balance"))
   		    .header("Authorization", "Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg==")
   		    .method("GET", HttpRequest.BodyPublishers.noBody())
   		    .build();
   		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			return response;
	}
	
	
}