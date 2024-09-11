package com.example.demo.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ApproveResponseDTO;
import com.example.demo.dto.ReadyResponseDTO;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.OrderDetail;
import com.example.demo.repository.model.ReadyTossDTO;
import com.example.demo.repository.model.User;
import com.example.demo.service.KakaoPayService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.example.demo.utils.SessionUtils;
import com.fasterxml.jackson.core.JsonParser;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RequestMapping("/cash")
@Controller
@RequiredArgsConstructor
public class CashController {
	
	private HttpSession httpSession;
	private final KakaoPayService kakaoPayService;
	private final OrderService orderService;
	private final UserService userService;
	
	/**
	 *  캐쉬 충전 페이지
	 * @return : 캐쉬 충전 페이지로 이동
	 */
	// http://localhost:8080/cash/charge
	@GetMapping("/charge")
	public String chargeCash(Model model) {
		return "/cash/chargeCash";
	}
	
	/**
	 * 카카오 페이 - 결제 요청 페이지
	 * @param txId
	 * @param paymentId
	 * @return
	 */
	// http://localhost:8080/cash/send-request-kakao
	@PostMapping("/send-request/kakao/{totalAmount}")
	@ResponseBody
	public ReadyResponseDTO payCashByKakao(@PathVariable("totalAmount") String totalAmount) {
		
		int totalPrice = Integer.valueOf(totalAmount); // 결제 가격
		String orderId = orderService.makeNewOrder(1,totalPrice,1); // 가주문 생성
		ReadyResponseDTO readyResponseDTO = kakaoPayService.payReady(totalPrice,orderId); //  결제 요청
		SessionUtils.addAtribute("tid", readyResponseDTO.getTid()); // tid
		orderService.setTid(orderId, readyResponseDTO.getTid());
		
		return readyResponseDTO;
	}
	
	/**
	 * 카카오 페이 - 결제 승인 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/result/kakao")
	public String resultKakaoPay(@RequestParam("pg_token") String pgToken) {
		
		// tid 가져오기
	    String tid = SessionUtils.getStringAttributeValue("tid");
	    // 주문 세부사항 가져오기
	    OrderDetail orderDetail = orderService.checkOrder(tid);
	    // 예외 처리
	    if(orderDetail == null) {
	    	// 예외 처리
	    	return null;
	    }
	    int userId = 1;
	    
	    // 주문 세부사항을 통해 가주문 - 진주문 확인 (인증)
	    Order checkOrderRecord = orderService.checkOrderRecord(userId,orderDetail);
	    
	    // 인증된 유저는 카카오 결제 요청
	    ApproveResponseDTO approveResponseDTO = kakaoPayService.payApprove(tid, pgToken);
	    
	    // 결제 후 -> 유저 캐쉬 변경
	    userService.updateUserCash(userId,checkOrderRecord.amount);
	    
	    // TODO - 캐쉬 히스토리 생성
	    
	    // 유저 캐쉬+
	    return "/cash/chargeResultKakao";
	}
	
	
	/**
	 * 카카오 페이 - 결제 취소 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/canceled/kakao")
	public String canceledKakaoPay(@RequestParam("pg_token") String pgToken) {
		
	    return "/cash/chargeCanceledToss";
	}
	
	/**
	 * 카카오 페이 - 결제 실패 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/failed/kakao")
	public String failedKakaoPay(@RequestParam("pg_token") String pgToken) {
		
	    return "/cash/chargeFailedToss";
	}

	
	/**
	 * 토스 - 결제 완료
	 * @param pgToken
	 * @return
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@PostMapping("/send-request-toss/{totalAmount}")
	@ResponseBody
	public ReadyTossDTO payCashToss(@PathVariable("totalAmount") String totalAmount) throws IOException, InterruptedException {
		
		String name = totalAmount+"원 캐쉬 충전";
		int totalPrice = Integer.valueOf(totalAmount);
		System.out.println("로깅1");
		RestTemplate restTemplate = new RestTemplate();
		org.springframework.http.HttpHeaders headers = makeTossHeaders();
		Map<String, String> parameters = new HashMap<>();
		parameters.put("amount", totalAmount);
		parameters.put("orderId", name);
		parameters.put("paymentKey", "test_sk_4yKeq5bgrpyxEo7Ndn5p8GX0lzW6");
		return restTemplate.postForObject(null, new HttpEntity<>(parameters,headers), ReadyTossDTO.class);
		
	}
	
	/**
	 * 토스 페이먼츠 결제를 위한 헤더 만들기
	 * @return
	 */
	public org.springframework.http.HttpHeaders makeTossHeaders() {
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("url", "https://api.tosspayments.com/v1/payments/confirm");
        return headers;
	}
	

	

}
