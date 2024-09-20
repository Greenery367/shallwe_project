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

	/**
	 * 현재 지급 가능한 금액 체크
	 * @param exchageRecord
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Boolean sendRequestExchange(RegisterExchange exchageRecord) throws IOException, InterruptedException {

		// 1. 현재 사이트가 지급할 수 있는 잔액 체크 
		// (1) HttpMessage 만들기
		HttpRequest request = HttpRequest.newBuilder()
		    .uri(URI.create("https://api.tosspayments.com/v1/payouts/sub-malls/settlements/balance"))
		    .header("Authorization", "Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg==")
		    .method("GET", HttpRequest.BodyPublishers.noBody())
		    .build();
		
		// (2) 응답 받아오기
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
		System.out.println(response.body());
		String responseBody = response.body();
        Long balance = extractBalance(responseBody);
        System.out.println("----------------잔액 췍"+balance);
              
        //임의로 샘플 값 넣기
        balance = 30000000000000L;
              
		  // 2. 만약 잔액이 충분하다면 true, 아니라면 false
          if(balance >= exchageRecord.getAmount()) {
        	  return true;
          } else {
        	  return false;
          }
	}
	
	
   /**
    * Balance 값 (현재 지급 가능액) String에서 추출 
    * @param json
    * @return
    */
   private static Long extractBalance(String json) {
       String balanceKey = "\"balance\":";
       int startIndex = json.indexOf(balanceKey);

       if (startIndex == -1) {
           throw new IllegalArgumentException("Balance key not found in the JSON response.");
       }

       startIndex += balanceKey.length();
       int endIndex = json.indexOf(",", startIndex);
       
       // endIndex가 없으면 객체가 종료된 위치를 찾아야 함
       if (endIndex == -1) {
           endIndex = json.indexOf("}", startIndex);
       }

       String balanceValue = json.substring(startIndex, endIndex).trim();
       
       // balanceValue를 Long으로 변환하여 반환
       return Long.parseLong(balanceValue);
   }

   
   /**
    * 환전 요청하기
    * @param exchageRecord
    * @throws IOException
    * @throws InterruptedException
    */
   public void sendExchangeRequest(RegisterExchange exchageRecord) throws IOException, InterruptedException {
	   String requestBody = String.format(
	            "[{\"subMallId\":\"%s\",\"payoutAmount\":%d,\"payoutDate\":\"%s\"}," +
	            "{\"subMallId\":\"%s\",\"payoutAmount\":%d,\"payoutDate\":\"%s\"}]",
	            exchageRecord.getSubmallId(), // 서브몰 ID 
	            exchageRecord.getAmount()*0.7, // 환전 신청 액수
	            "2024-09-03",  // 지급일
	            exchageRecord.getSubmallId(), // 서브몰 ID 
	            exchageRecord.getAmount(), // 환전 신청 액수
	            "2024-09-03"   // 지급일
	        );
	   
	   HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("https://api.tosspayments.com/v1/payouts/sub-malls/settlements"))
			.header("Authorization", "Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg==")
			.header("Content-Type", "application/json")
			.method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
		    .build();
	    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		return;
	}

   /**
    * 환전 신청 상태 변경
    * @param exchageRecord
    */
   public void setStatusToFinished(RegisterExchange exchageRecord) {
		registerExchangeRepository.updateExchangeStatusToFinished(exchageRecord.getId());
		return ;
	}
	
}