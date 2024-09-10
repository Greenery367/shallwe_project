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
import com.example.demo.repository.model.ReadyTossDTO;
import com.example.demo.repository.model.User;
import com.example.demo.service.KakaoPayService;
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
	 * 캐쉬 충전 조회 페이지
	 * @param txId
	 * @param paymentId
	 * @return
	 */
	// http://localhost:8080/cash/send-request-kakao
	@PostMapping("/send-request-kakao/{totalAmount}")
	@ResponseBody
	public ReadyResponseDTO payCash(@PathVariable("totalAmount") String totalAmount) {
		
		String name = totalAmount+"원 캐쉬 충전";
		int totalPrice = Integer.valueOf(totalAmount);
		
		ReadyResponseDTO readyResponseDTO = kakaoPayService.payReady(name, totalPrice);
		
		SessionUtils.addAtribute("tid", readyResponseDTO.getTid());
		return readyResponseDTO;
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
		System.out.println("로깅2");
		System.out.println("headers : "+headers);
		System.out.println("parameters : "+parameters);
		System.out.println(restTemplate.postForObject(null, new HttpEntity<>(parameters,headers), ReadyTossDTO.class));
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
	
	@GetMapping("/success")
	private String resultPayByToss1(Model model) {
		String status = "success";
		model.addAttribute("status", status);
		return "/cash/chargeResultToss";
	}
	
	@GetMapping("/fail")
	private String resultPayByToss2(Model model) {
		String status = "success";
		model.addAttribute("status", status);
		return "/cash/chargeResultToss";
	}
	

}
