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
	
	/**
	 * 모든 서브몰 객체 조회
	 * @param limit
	 * @param offset
	 * @return
	 */
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
		UUID uuid = UUID.randomUUID(); // 랜덤값 생성
		String orderId = (String)(user.getUserId()+"_"+uuid); // 서브몰 ID 생성
		String submallId = orderId.substring(0,19); // 서브몰 ID 20자리 제한 - 20번째에서 자르기
		
		BankDTO bankInfo = bankService.getAccountByUserIdAndBankId(user.getUserId()); // 유저 명의의 bank 객체 찾기
		
		// 서브몰 객체 생성
		Submall newSubmall = Submall.builder().submallId(submallId)
											.accountId(bank.getBankId())
											.email(user.getEmail())
											.phoneNumber(user.getPhoneNumber())
											.build();
		// JSON 문자열을 직접 생성
        String requestBody = String.format(
        		"{\"subMallId\":\"%s\",\"account\":{\"bank\":\"%s\",\"accountNumber\":\"%s\",\"holderName\":\"%s\"}," +
		        "\"type\":\"CORPORATE\",\"email\":\"%s\",\"phoneNumber\":\"%s\",\"companyName\":\"%s\"," +
		        "\"representativeName\":\"%s\",\"businessNumber\":\"%s\"}",
		        newSubmall.getSubmallId(), // 서브몰 ID
	            bankInfo.getBankId(), // 은행 고유 코드
                bankInfo.getAccountNumber(), // 계좌번호
                user.getUsername(), // 계좌 주
		        newSubmall.getEmail(), // 이메일
		        newSubmall.getPhoneNumber(), // 전화번호
		        "셸위-게임 친구 매칭 사이트", // 회사명
		        user.getUsername(), // 대표명
	            "1200220000" // 사업자번호
		    );
		
		
        // Http-POST 메세지 만들기
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.tosspayments.com/v1/payouts/sub-malls"))
			    .header("Authorization", "Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg==")
			    .header("Content-Type", "application/json")
			    .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
			    .build();
		
		// TOSS 서버에서 응답 받아오기 - 서브몰 등록
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		// 응답 메세지 String 으로 변환
		String result = response.toString();
		
		
		// 결과가 정상적으로 반환되었다면
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
