package com.example.demo.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.BankDTO;
import com.example.demo.dto.BankInfoDTO;
import com.example.demo.dto.ReadyResponseDTO;
import com.example.demo.dto.SubmallInfoDTO;
import com.example.demo.repository.SubmallRepository;
import com.example.demo.repository.model.RegisterSubmall;
import com.example.demo.repository.model.Submall;

@Service
public class SubmallService {
	
	@Autowired
	private SubmallRepository submallRepository;
	
	/**
	 * 모든 서브몰 신청 내역 조회
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<RegisterSubmall> getllAllSubmallRegitser(int limit, int offset){
		return submallRepository.selectAllRegisteredSubmall(limit, offset);
	}
	
	/**
	 * 유저 정보를 통해 계좌 정보 조회
	 * @param userId
	 * @return
	 */
	public BankDTO getBankInfoById(int userId) {
		return submallRepository.getBankInfo(userId);
	}
	
	/**
	 * 새로운 서브몰 등록
	 * @return 
	 */
	public Submall makeNewSubmall(SubmallInfoDTO submallInfo) {
		// 서브몰 ID 생성
		System.out.println("1111");
		UUID uuid = UUID.randomUUID(); // 랜덤값 생성
		String orderId = (String)(submallInfo.user.getUserId()+"_"+uuid);
		String submallId = orderId.substring(0,19);
		
		// 서브몰 객체 생성
		System.out.println("2222");
		Submall newSubmall = Submall.builder().submallId(submallId)
											.accountId(submallInfo.bankInfo.getBankId())
											.email(submallInfo.getUser().getEmail())
											.phoneNumber(submallInfo.user.getPhoneNumber())
											.build();
		
		// HTTPEntity - 헤더 생성
		System.out.println("3333");
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Authorization", "Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg==");
		headers.set("Content-type", "application/json");
		
		// HTTPEntity - 바디 생성
		System.out.println("4444");
		Map<String, String> parameters = new HashMap<>();
		parameters.put("subMallId", submallId);
		parameters.put("account", "{'bank':"+submallInfo.getBankInfo().getBankId()+",accountNumber:"+submallInfo.getBankInfo().getAccountNumber()+"}");
		parameters.put("type", "INDIVIDUAL");
		parameters.put("email", submallInfo.getUser().getEmail());
		parameters.put("phoneNumber", submallInfo.user.getPhoneNumber());
		
		// http 메세지 만들기
		HttpEntity<Map<String,String>> requestEntity = new HttpEntity(parameters,headers);
		RestTemplate template = new RestTemplate();
		String url = "https://api.tosspayments.com/v1/payouts/sub-malls";
				
		// http response 값 반환
		ResponseEntity<Submall> responseEntity = template.postForEntity(url, requestEntity, Submall.class);

		System.out.println(responseEntity);
		
		// 서브몰 객체 DB에 생성
		submallRepository.addNewSubmall(newSubmall.submallId,newSubmall.accountId,newSubmall.email,newSubmall.phoneNumber);

		return responseEntity.getBody();
		
	}

}
