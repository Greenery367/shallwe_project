package com.example.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.OAuthToken;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class test {
	
	@GetMapping("kakaoLogin")
	public String getCode(@RequestParam(name ="code") String code) {
		RestTemplate rt1 = new RestTemplate();
		
		HttpHeaders header1 = new HttpHeaders();
		header1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<>();
		params1.add("grant_type", "authorization_code");
		params1.add("client_id", "61e5a7465f3248de0332e984c98de103");
		params1.add("redirect_uri", "http://localhost:8080/test/kakaoLogin");
		params1.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> reqKakaoMessage = new HttpEntity<>(params1, header1);
		ResponseEntity<OAuthToken> response1 = rt1.exchange
				("https://kauth.kakao.com/oauth/token", HttpMethod.POST, reqKakaoMessage, OAuthToken.class);
		
		return "";
	}
	
	
	@GetMapping("/test/mainPage")
	public String abccc() {
		return "mainPage";
	}
	
	@GetMapping("/test/signIn")
	public String signIn() {
		return "sign/signIn";
	}
	
	@GetMapping("/test/signUp")
	public String signUp() {
		return "sign/signUp";
	}
	
	@GetMapping("/test/id")
	public String getMethodName() {
		return new String();
	}
	
	@GetMapping("/test/idCheck")
	public String abc(@RequestParam(name = "id") String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "sign/idCheck";
	}
	
	
}
