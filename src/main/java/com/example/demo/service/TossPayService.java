package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
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
	 * 토스 페이 - 결제 요청
	 * @param secretKey
	 * @param paymentKey
	 * @param amount
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 */
	public TossPayApproveResponseDTO sendTossPayRequest(String secretKey, String paymentKey, Long amount) throws UnsupportedEncodingException, URISyntaxException {
		secretKey = "test_ck_ALnQvDd2VJzdqzNAkgNYVMj7X41m";
        String baseUrl = "https://api.tosspayments.com/v1/payments/" + paymentKey;

        // Basic Authentication을 위한 secretKey 인코딩
        String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes("UTF-8"));

        // 요청 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.set("Content-Type", "application/json");
        
        // 요청 바디 생성
        Map<String, String> parameters = new HashMap<>();
        parameters.put("paymentKey", "5EnNZRJGvaBX7zk2yd8ydw26XvwXkLrx9POLqKQjmAw4b0e1");
        parameters.put("orderId", "a4CWyWY5m89PNh7xJwhk1");
        parameters.put("amount", "10000");
     	
     	 // 요청 엔티티 설정 (본문 없음)
        HttpEntity<Map<String, String>> requestEntity= new HttpEntity<>(parameters, headers);

        // RestTemplate 인스턴스 생성
        RestTemplate restTemplate = new RestTemplate();

        // URI 생성
        URI uri = new URI(baseUrl);

        // GET 요청 수행
        ResponseEntity<TossPayApproveResponseDTO> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, TossPayApproveResponseDTO.class);
        
        // response body 분리
        TossPayApproveResponseDTO responseDto =  responseEntity.getBody();

     	SessionUtils.addAtribute("tid", responseDto.getPaymentKey()); // tid
        
        // 가주문 생성
        // 주문 세부사항 만들기
     	OrderDetail orderDetail = OrderDetail.builder()
     										.orderId(responseDto.getPaymentKey())
     										.cid("TC0ONETIME")
     										.tid(responseDto.getPaymentKey())
     										.partnerOrderId(responseDto.getPaymentKey())
     										.partnerUserId("partner_user_id")
     										.totalAmount(responseDto.getTotalAmount())
     										.itemName(responseDto.getTotalAmount()+"원 캐쉬 충전")
     										.taxFreeAmount(responseDto.getTaxFreeAmount())
     										.build();
     	
     	orderService.makeNewOrderDetail(orderDetail);
     	orderService.makeNewOrder(1, responseDto.getTotalAmount(), 2, responseDto.getPaymentKey() );
     	
     	
     	
        return responseDto;
        
	}
	
}
