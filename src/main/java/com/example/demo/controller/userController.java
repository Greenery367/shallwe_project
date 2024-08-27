package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.KakaoProfile;
import com.example.demo.dto.OAuthToken;
import com.example.demo.dto.SignUpDTO;
import com.example.demo.repository.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class userController {
	
	@Autowired
	private final UserService userService;
	@Autowired
	private final HttpSession session;
	
	
	@Value("${tenco.key}")
	private String tencoKey;
	
	
	/**
	 * 메인 페이지 이동
	 * @return
	 */
	@GetMapping("/main")
	public String mainPage() {
		return "mainPage";
	}
	
	@GetMapping("/kakaoLogin")
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
		
		RestTemplate rt2 = new RestTemplate();
		
		HttpHeaders headers2 = new HttpHeaders();
		
		headers2.add("Authorization", "Bearer " + response1.getBody().getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> reqKakaoInfoMessage = new HttpEntity<>(headers2);
		
		ResponseEntity<KakaoProfile> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST, reqKakaoInfoMessage, KakaoProfile.class);
		
		KakaoProfile kakaoProfile = response2.getBody();
		
		User oldUser = userService.searchUserName(String.valueOf(kakaoProfile.getId()));
		if(oldUser == null) {
			// 사용자가 최초로 소셜 로그인을 하는 사람
			
		}
		
		session.setAttribute("principal", oldUser);
		return "redirect:/user/main";
	}
	
	
	@GetMapping("/signIn")
	public String signIn() {
		return "sign/signIn";
	}
	
	@GetMapping("/signUp")
	public String signUp() {
		return "sign/signUp";
	}

	
	@GetMapping("/idCheck")
	public String abc(@RequestParam(name = "id") String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "sign/idCheck";
	}
}
