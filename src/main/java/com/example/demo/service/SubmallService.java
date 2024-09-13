package com.example.demo.service;

import java.io.IOException;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.BankDTO;
import com.example.demo.dto.BankInfoDTO;
import com.example.demo.dto.ReadyResponseDTO;
import com.example.demo.dto.RegisterSubmallDTO;
import com.example.demo.dto.SubmallInfoDTO;
import com.example.demo.repository.RegisterSubmallRepositroy;
import com.example.demo.repository.SubmallRepository;
import com.example.demo.repository.model.RegisterSubmall;
import com.example.demo.repository.model.Submall;
import com.example.demo.repository.model.User;

@Service
public class SubmallService {
	
	@Autowired
	private SubmallRepository submallRepository;
	
	@Autowired
	private RegisterSubmallRepositroy registerSubmallRepositroy;
	
	@Autowired
	private BankService bankService;
	
	/**
	 * 모든 서브몰 신청 내역 조회
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<RegisterSubmall> getllAllSubmallRegitser(int limit, int offset){
		return submallRepository.selectAllRegisteredSubmall(limit, offset);
	}
	
	public List<Submall> getllAllSubmall(int limit, int offset){
		return submallRepository.selectAllSubmall(limit, offset);
	}
	
	
	/**
	 * 유저 정보를 통해 계좌 정보 조회
	 * @param userId
	 * @return
	 */
	public BankDTO getBankInfoById(int userId) {
		return registerSubmallRepositroy.getBankInfo(userId);
	}
	
	/**
	 * 새로운 서브몰 등록
	 * @return 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public boolean makeNewSubmall(RegisterSubmall submall, User user, BankDTO bank) throws IOException, InterruptedException {
		// 서브몰 ID 생성
		System.out.println("-------------1111");
		UUID uuid = UUID.randomUUID(); // 랜덤값 생성
		String orderId = (String)(user.getUserId()+"_"+uuid);
		String submallId = orderId.substring(0,19);
		
		System.out.println("-----------orderId : "+orderId);
		System.out.println("-----------submallId : "+submallId);
		
		BankDTO bankInfo = bankService.getAccountByUserIdAndBankId(user.getUserId());

		System.out.println("--------------bankInfo : "+bankInfo);
		
		// 서브몰 객체 생성
		System.out.println("--------------2222");
		Submall newSubmall = Submall.builder().submallId(submallId)
											.accountId(bank.getBankId())
											.email(user.getEmail())
											.phoneNumber(user.getPhoneNumber())
											.build();
		
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.tosspayments.com/v1/payouts/sub-malls"))
			    .header("Authorization", "Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg==")
			    .header("Content-Type", "application/json")
			    .method("POST", HttpRequest.BodyPublishers.ofString("{\"subMallId\":\"testmall1dssdf00\",\"account\":{\"bank\":\"03\",\"accountNumber\":\"34000000000011\",\"holderName\":\"김토페\"},\"type\":\"CORPORATE\",\"email\":\"example@email.com\",\"phoneNumber\":\"01012341234\",\"companyName\":\"테스트몰100\",\"representativeName\":\"김토페\",\"businessNumber\":\"1200220000\"}"))
			    .build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		System.out.println(response.body());
		
		String result = response.toString();
		
		if(result != null || !result.contains("null")) {
			// 서브몰 객체 DB에 생성
			submallRepository.addNewSubmall(newSubmall.submallId,user.getUserId(),newSubmall.accountId,newSubmall.email,newSubmall.phoneNumber);
			// 서브몰 신청내역 상태 변경
			submallRepository.updateRegisterSubmallStatus(submall.id);
			
			return true;
		} else {
			return false;
		}
	}

}
