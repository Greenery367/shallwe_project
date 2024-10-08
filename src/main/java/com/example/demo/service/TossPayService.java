package com.example.demo.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.tomcat.util.json.JSONParser;
import org.eclipse.tags.shaded.org.apache.xalan.templates.ElemValueOf;
import org.h2.util.json.JSONArray;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ReadyTossDTO;
import com.example.demo.dto.TossPayApproveResponseDTO;
import com.example.demo.repository.model.OrderDetail;
import com.example.demo.utils.SessionUtils;

@Service
public class TossPayService {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 토스 페이 - 결제 정보 저장 페이지
	 * @param orderId2 
	 * @param secretKey
	 * @param paymentKey
	 * @param amount
	 * @return 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 */
	public String sendTossPayRequest(String paymentKey, String orderId, int userId, Long amount) throws UnsupportedEncodingException, URISyntaxException {
		
        String userIdStr = String.valueOf(userId);
		
        // 가주문 생성
        // 주문 세부사항 만들기
     	OrderDetail orderDetail = OrderDetail.builder()
     										.orderId(paymentKey)
     										.cid("TC0ONETIME")
     										.partnerOrderId(paymentKey)
     										.partnerUserId(userIdStr)
     										.itemName(amount+"원 캐쉬 충전")
     										.totalAmount(Integer.parseInt(amount.toString()))
     										.taxFreeAmount(0)
     										.build();
     	
     	// 진짜 주문도 생성..
     	orderService.makeNewOrderDetail(orderDetail);
     	orderService.makeNewOrder(userId, amount, 2, paymentKey);


     	
     	System.out.println("~~~~~~주문 생성"+paymentKey);
     	
     	
     	// response 리턴
        return paymentKey;
        
	}
	

	/**
	 * 토스 페이먼츠 결제 요청
	 * @param amount 
	 * @param paymentKey 
	 * @param orderId 
	 * @return 
	 * @return 
	 * @throws URISyntaxException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public  String sendTossPayRequestFinish(String orderId, String paymentKey, Long amount) throws URISyntaxException, IOException, InterruptedException {
		String base64 = "Base64 인증키";
	    String baseUrl = "https://api.tosspayments.com/v1/payments/confirm";

	    // 요청 본문을 JSON 문자열로 생성
	    String requestBody = String.format(
	        "{\"paymentKey\":\"%s\",\"orderId\":\"%s\",\"amount\":%d}",
	        paymentKey, // 결제 키
	        orderId, // 주문 ID
	        amount // 금액
	    );

	    HttpRequest request = HttpRequest.newBuilder()
	        .uri(URI.create(baseUrl))
	        .header("Authorization", "Base64 인증키")
	        .header("Content-Type", "application/json")
	        .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
	        .build();

	    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
	    System.out.println(response.body());

	    return response.body();

	}

	/**
	 * 결제 완료 처리
	 * @param responseEntity
	 */
	public String sendFinishTossPayment(TossPayApproveResponseDTO responseEntity) {
			String orderId = responseEntity.getPaymentKey();
	        int amount = responseEntity.getTotalAmount();

	        // Toss Payments API URL
	        String url = "https://api.tosspayments.com/v1/payments/" + orderId;

	        // 헤더 설정
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Bearer YOUR_ACCESS_TOKEN"); // 필요한 경우 토큰 추가
	        headers.set("Content-Type", "application/json");

	        // 요청 바디 설정
	        Map<String, Object> requestBody = new HashMap<>();
	        requestBody.put("orderId", orderId);
	        requestBody.put("amount", amount);
	        // 필요한 다른 필드 추가...

	        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

	        // RestTemplate 인스턴스 생성
	        RestTemplate restTemplate = new RestTemplate();
	        
	        // API 요청 보내기
	        ResponseEntity<String> responseEntity1 = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
	        System.out.println(responseEntity1);
	        return responseEntity1.getBody(); // 응답 반환
	}
	
}
