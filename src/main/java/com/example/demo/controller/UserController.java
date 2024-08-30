package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class UserController {
	
	private final UserService userService = null;
	private final HttpSession session = null;
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	

	
	@Value("${tenco.key}")
	private String tencoKey;
	
	
	@GetMapping("/kakao")
	public String getCode(@RequestParam(name ="code") String code, Model model) throws ParseException {
		

		
		RestTemplate rt1 = new RestTemplate();
		
		HttpHeaders header1 = new HttpHeaders();
		header1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<>();
		params1.add("grant_type", "authorization_code");
		params1.add("client_id", "61e5a7465f3248de0332e984c98de103");
		params1.add("redirect_uri","http://localhost:8080/user/kakao");
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
		User oldUser = userService.searchId("kakao_auth"+String.valueOf(kakaoProfile.getId()));
		if(oldUser == null) {
			// 사용자가 최초로 소셜 로그인을 하는 사람
			SignUpDTO dto = new SignUpDTO();
			dto.setId("kakao_auth"+String.valueOf(kakaoProfile.getId()));
			dto.setNickname(kakaoProfile.getProperties().getNickname());
			model.addAttribute("dto", dto);
			return "sign/kakaoSignUp";
		}
		
		session.setAttribute("principal", oldUser);
		return "redirect:/user/main";
	}
	
	
	@PostMapping("/kakaoSignUp")
	public String kakaoSignUp(SignUpDTO dto, Model model, HttpServletRequest request) {
		dto.setPassword(tencoKey);
		dto.setUsername(request.getParameter("username"));
		dto.setBirthDate(request.getParameter("birthDate"));
		dto.setEmail(request.getParameter("emailBody")+"@"+request.getParameter("emailDomain"));
		dto.setPhoneNumber(request.getParameter("phoneNumber"));
		userService.createUser(dto);
		return "sign/signIn";
	}
	
	/*
	 * 로그인 페이지 요청 
	 */
	@GetMapping("/signIn")
	public String signIn() {
		return "sign/signIn";
	}
	
	/*
	 * 회원가입 페이지 요청
	 */
	@GetMapping("/signUp")
	public String signUp() {
		return "sign/signUp";
	}
	
	/*
	 * 회원가입 폼 -> 회원가입 처리 요청
	 */
	@PostMapping("/signUp")
	public String postMethodName(HttpServletRequest request) {
		SignUpDTO dto = new SignUpDTO();
		dto.setId(request.getParameter("id"));
		dto.setNickname(request.getParameter("nickname"));
		dto.setPassword(tencoKey);
		dto.setUsername(request.getParameter("username"));
		dto.setBirthDate(request.getParameter("birthDate"));
		dto.setEmail(request.getParameter("emailBody")+"@"+request.getParameter("emailDomain"));
		dto.setPhoneNumber(request.getParameter("phoneNumber"));
		userService.createUser(dto);
		return "sign/signIn";
	}
	
	@PostMapping("/checkId")
 	public ResponseEntity<Map<String, String>> checkId(@RequestParam("id") String id) {
	       boolean isAvailable = userService.isIdAvailable(id);
	       Map<String, String> response = new HashMap<>();
	       response.put("result", isAvailable ? "available" : "unavailable");
	       return ResponseEntity.ok(response);
	}
	
	

	
	@GetMapping("/idCheck")
	public String abc(@RequestParam(name = "id") String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "sign/idCheck";
	}

}
