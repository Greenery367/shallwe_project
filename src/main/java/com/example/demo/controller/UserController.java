
package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.example.demo.service.EmailSendService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;




@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private final UserService userService;
	@Autowired
	private final HttpSession session;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	@Autowired
	private final EmailSendService emailSendService;
	private Integer authNumber;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Random random = new Random();

	
	@Value("${tenco.key}")
	private String tencoKey;
	
	
	/**
	 * 메인 페이지 이동
	 * @return
	 */
	// http://localhost:8080/user/main
	@GetMapping("/main")
	public String mainPage() {
		return "mainPage";
	}
	
	/*
	 * 아이디 찾기 창 요청
	 */
	@GetMapping("find-id")
	public String findId() {
		return "sign/findId";
	}
	
	/*
	 * 아이디 찾기 실행 요청
	 */
	@PostMapping("find-id")
	public String findId(HttpServletRequest request) {
		String username = request.getParameter("username");
		String nickname = request.getParameter("username");
		String emailBody = request.getParameter("emailBody");
		String emailDomain = request.getParameter("emailDomain");
		String email = emailBody+"@"+emailDomain;
		
		User user = userService.searchByEmail(email);
		
		if(user.getNickname().equals(nickname) || user.getUsername().equals(username)) {
			String id = user.getId();
			String subject = "쉘위 아이디 찾기 요청 메일입니다.";
	        String content = "안녕하세요! 회원님. <br>" +
	        		"요청하신 아이디는 " + id + "입니다. <br>" +
	        		"감사합니다.";
			// 이메일로 ID 발송 로직 구현 
	        emailSendService.idMailSend("yourservice@example.com", email, subject, content);
			request.setAttribute("msg", "입력하신 이메일로 ID가 발송되었습니다.");
	        request.setAttribute("url", "sign-in");
	        return "alert";
		}else {
			request.setAttribute("msg", "이름 또는 닉네임이 정확하지 않습니다.");
	        request.setAttribute("url", "find-id");
	        return "alert";
		}
	}
	
	
	
	@GetMapping("find-pw")
	public String findPw() {
		return "sign/findPw";
	}
	
	/**
	 * 비밀번호 찾기 요청
	 * @param request
	 * @return
	 */
	@Transactional
	@PostMapping("find-pw")
	public String findPw(HttpServletRequest request) {
		String id = request.getParameter("id");
		String emailBody = request.getParameter("emailBody");
		String emailDomain = request.getParameter("emailDomain");
		String email = emailBody+"@"+emailDomain;
		
		User user = userService.searchId(id);
		
		if(user == null) {
			request.setAttribute("msg", "존재하지 않는 ID 입니다.");
	        request.setAttribute("url", "find-pw");
	        return "alert";
		}
		
		if(user.getEmail().equals(email)) {
			String temporaryPassword = emailSendService.pwMailSend(email);
			int result = userService.temporaryPassword(temporaryPassword, email);
			if(result != 1) {
				request.setAttribute("msg", "비밀번호 찾기에 실패했습니다. 잠시 후 다시 시도해 보세요.");
		        request.setAttribute("url", "find-pw");
		        return "alert";
			}else {
				request.setAttribute("msg", "입력하신 메일로 비밀번호가 발송되었습니다. ");
		        request.setAttribute("url", "sign-in");
		        return "alert";
			}
		}else {
			request.setAttribute("msg", "입력하신 ID와 메일이 일치하지 않습니다.");
	        request.setAttribute("url", "find-pw");
	        return "alert";
		}
	}
	
	public void makeRandomNum() {
        Random r = new Random();
        String randomNumber = "";
        for(int i = 0; i < 6; i++) {
            randomNumber += Integer.toString(r.nextInt(10));
        }

        authNumber = Integer.parseInt(randomNumber);
    }
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
			dto.setNickname(kakaoProfile.getProperties().getNickname()+"#"+random.nextInt(9000)+1000);
			model.addAttribute("dto", dto);
			return "sign/kakaoSignUp";
		}
		
		session.setAttribute("principal", oldUser);
		return "redirect:/user/main";
	}
	
	
	@PostMapping("/kakao-sign-up")
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
	@GetMapping("/sign-in")
	public String signIn() {
		return "sign/signIn";
	}
	
	/*
	 * 로그인 페이지 --> 로그인 요청
	 */
	@PostMapping("/sign-in")
	public String signIn(HttpServletRequest request) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		User user = userService.searchId(id); 
		if(user == null) {
			request.setAttribute("msg", "존재하지 않는 ID입니다.");
	        request.setAttribute("url", "sign-in");
	        return "alert";
	    }else{
	    	if(passwordEncoder.matches(password, user.getPassword())) {
	    		session.setAttribute("principal", user);
	    		return "redirect:/user/main";
	    	}else {
	    		request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
		        request.setAttribute("url", "sign-in");
		        return "alert";
	    	}
	    }
	}
	
	
	
	
	/*
	 * 회원가입 페이지 요청
	 */
	@GetMapping("/sign-up")
	public String signUp() {
		return "sign/signUp";
	}
	
	/*
	 * 회원가입 폼 -> 회원가입 처리 요청
	 */
	@PostMapping("/sign-up")
	public String postMethodName(HttpServletRequest request) {
		SignUpDTO dto = new SignUpDTO();
		dto.setId(request.getParameter("id"));
		dto.setNickname(request.getParameter("nickname"));
		dto.setPassword(request.getParameter("password"));
		dto.setUsername(request.getParameter("username"));
		dto.setBirthDate(request.getParameter("birthDate"));
		dto.setEmail(request.getParameter("emailBody")+"@"+request.getParameter("emailDomain"));
		dto.setPhoneNumber(request.getParameter("phoneNumber"));
		userService.createUser(dto);
		request.setAttribute("msg", "회원가입 완료.");
        request.setAttribute("url", "sign-in");
		return "alert";
	}
	
	@PostMapping("/check-id")
 	public ResponseEntity<Map<String, String>> checkId(@RequestParam("id") String id) {
	       boolean isAvailable = userService.isIdAvailable(id);
	       Map<String, String> response = new HashMap<>();
	       response.put("result", isAvailable ? "available" : "unavailable");
	       return ResponseEntity.ok(response);
	}
	
	@PostMapping("/check-nickname")
 	public ResponseEntity<Map<String, String>> checkNickname(@RequestParam("nickname") String nickname) {
	       boolean isAvailable = userService.isNicknameAvailable(nickname);
	       Map<String, String> response = new HashMap<>();
	       response.put("result", isAvailable ? "available" : "unavailable");
	       return ResponseEntity.ok(response);
	}

	@PostMapping("/check-email")
 	public ResponseEntity<Map<String, String>> checkEmail(@RequestParam("email") String email) {
	       boolean isAvailable = userService.isEmailAvailable(email);
	       Map<String, String> response = new HashMap<>();
	       response.put("result", isAvailable ? "available" : "unavailable");
	       return ResponseEntity.ok(response);
	}
	
	@GetMapping("/idCheck")
	public String abc(@RequestParam(name = "id") String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "sign/idCheck";
	}

	@GetMapping("/findUser")
	public String getMethodName() {
		return "friend/findFriend";
	}
	
	@PostMapping("/findUser")
	public String postMethodName(@RequestParam(name="name") String name,
			@RequestParam(name="pageNum")int page , HttpServletRequest request) {
		// 한 페이지에 유저가 10명씩 보이도록 설정
		int limit = 10;
		// 오프셋은 limit * (page - 1)
		int offset = limit * (page - 1);
		List<User>userList = userService.findLikeUser(name,limit,offset);
		int size = userService.findLikeUserSize(name);
		int pageNum = (int)Math.ceil(size / limit);
		request.setAttribute("userList", userList); // 검색된 유저리스트
		request.setAttribute("name", name); // 검색어 
		request.setAttribute("current",page); // 현재 페이지
		request.setAttribute("pageSize", pageNum); // 총 페이지 수
		return "friend/findFriend";
	}
	
	@PostMapping("/sendFriend")
	public void postMethodName(@RequestParam(name="userId")int user,
			@RequestParam(name="friendId")int friend) {
		userService.insertWaitingFriend(user, friend);
	}
	
}
