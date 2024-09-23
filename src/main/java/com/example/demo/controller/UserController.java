
package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.AlarmDTO;
import com.example.demo.dto.GoogleOauthToken;
import com.example.demo.dto.GoogleProfile;
import com.example.demo.dto.KakaoProfile;
import com.example.demo.dto.NaverOauthToken;
import com.example.demo.dto.NaverProfile;
import com.example.demo.dto.OAuthToken;
import com.example.demo.dto.SignUpDTO;
import com.example.demo.handler.exception.DataDeleveryException;
import com.example.demo.repository.model.Advertise;
import com.example.demo.repository.model.Alarm;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.Mbti;
import com.example.demo.repository.model.News;
import com.example.demo.repository.model.Notice;
import com.example.demo.repository.model.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.AlarmService;
import com.example.demo.service.EmailSendService;
import com.example.demo.service.FriendService;
import com.example.demo.service.MatchService;
import com.example.demo.service.MbtiService;
import com.example.demo.service.NoticeService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	private final AdminService adminService;
	@Autowired
	private final FriendService friendService;
	@Autowired
	private NoticeService noticeService;

	private final AlarmService alarmService;
	private final MatchService matchService;
	@Autowired
	private final HttpSession session;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	@Autowired
	private final EmailSendService emailSendService;

	@Autowired
	private final MbtiService mbtiService;

	private Integer authNumber;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Random random = new Random();

	@Value("${tenco.key}")
	private String tencoKey;

	@Value("${googleClientId.key}")
	private String googleClientId;

	@Value("${googleClientSecret.key}")
	private String googleClientSecret;

	@Value("${kakaoClientId.key}")
	private String kakaoClientId;

	@Value("${naverClientId.key}")
	private String naverClientId;

	@Value("${naverClientSecret.key}")
	private String naverClientSecret;

	/**
	 * 메인 페이지 이동
	 * 
	 * @return
	 */
	// http://localhost:8080/user/main
	@GetMapping("/main")
	public String mainPage(Model model) {

		User user = (User) session.getAttribute("principal");

		if (user != null) {
			List<User> onlineFriendList = friendService.checkOnlineFriend(user.getUserId());
			model.addAttribute("onlineFriends", onlineFriendList);
			model.addAttribute("user", user);
		}


		// 최신 공지사항 글 
		List<Notice> noticeList = noticeService.getAllNotice(0);
		List<News> newsList = noticeService.getAllnews();

		model.addAttribute("noticeList", noticeList);
		model.addAttribute("newsList", newsList);
		
		if(user != null) {
			List<User> onlineFriendList = friendService.checkOnlineFriend(user.getUserId());
			model.addAttribute("onlineFriends",onlineFriendList);
			model.addAttribute("user",user);
		} else {
			return "mainPage";
		}
		Mbti myMbti = mbtiService.getRecentMbtiInfo(user.getUserId());
		
		
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		
		model.addAttribute("advertiseListOne", advertiseListOne);
		model.addAttribute("advertiseListTwo", advertiseListTwo);
		model.addAttribute("advertiseListThree", advertiseListThree);

		model.addAttribute("categoryList", categoryList);

		model.addAttribute("categoryList",categoryList);
		model.addAttribute("myMbti",myMbti);
	
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
		String email = emailBody + "@" + emailDomain;

		User user = userService.searchByEmail(email);

		if (user.getNickname().equals(nickname) || user.getUsername().equals(username)) {
			String id = user.getId();
			String subject = "쉘위 아이디 찾기 요청 메일입니다.";
			String content = "안녕하세요! 회원님. <br>" + "요청하신 아이디는 " + id + "입니다. <br>" + "감사합니다.";
			// 이메일로 ID 발송 로직 구현
			emailSendService.idMailSend("yourservice@example.com", email, subject, content);
			request.setAttribute("msg", "입력하신 이메일로 ID가 발송되었습니다.");
			request.setAttribute("url", "sign-in");
			return "alert";
		} else {
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
	 * 
	 * @param request
	 * @return
	 */
	@Transactional
	@PostMapping("find-pw")
	public String findPw(HttpServletRequest request) {
		String id = request.getParameter("id");
		String emailBody = request.getParameter("emailBody");
		String emailDomain = request.getParameter("emailDomain");
		String email = emailBody + "@" + emailDomain;

		User user = userService.searchId(id);
		if (user == null) {
			request.setAttribute("msg", "존재하지 않는 ID 입니다.");
			request.setAttribute("url", "find-pw");
			return "alert";
		}

		if (user.getEmail().equals(email)) {
			String temporaryPassword = emailSendService.pwMailSend(email);
			int result = userService.temporaryPassword(temporaryPassword, email);
			if (result != 1) {
				request.setAttribute("msg", "비밀번호 찾기에 실패했습니다. 잠시 후 다시 시도해 보세요.");
				request.setAttribute("url", "find-pw");
				return "alert";
			} else {
				request.setAttribute("msg", "입력하신 메일로 비밀번호가 발송되었습니다. ");
				request.setAttribute("url", "sign-in");
				return "alert";
			}
		} else {
			request.setAttribute("msg", "입력하신 ID와 메일이 일치하지 않습니다.");
			request.setAttribute("url", "find-pw");
			return "alert";
		}
	}

	public void makeRandomNum() {
		Random r = new Random();
		String randomNumber = "";
		for (int i = 0; i < 6; i++) {
			randomNumber += Integer.toString(r.nextInt(10));
		}

		authNumber = Integer.parseInt(randomNumber);
	}

	/*
	 * 로그인 페이지 요청
	 */
	@GetMapping("/sign-in")
	public String signInReq() {
		return "sign/signIn";
	}

	/*
	 * 로그인 페이지 --> 로그인 요청
	 */
	@PostMapping("/sign-in")
	public String signIn(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (id == null || id.trim().length() == 0) {
			request.setAttribute("msg", "ID를 입력하세요");
			request.setAttribute("url", "sign-in");
			return "alert";
		}
		String password = request.getParameter("password");
		if (password == null || password.trim().length() == 0) {
			request.setAttribute("msg", "비밀번호를 입력하세요");
			request.setAttribute("url", "sign-in");
			return "alert";
		}
		User user = userService.searchId(id);
		if (user == null) {
			request.setAttribute("msg", "존재하지 않는 ID입니다.");
			request.setAttribute("url", "sign-in");
			return "alert";
		} else {
//	비밀번호 해싱 처리 개발단계에서 생략
			if (passwordEncoder.matches(password, user.getPassword())) {
				// if (password.equals(user.getPassword())) {
				session.setAttribute("principal", user);
				return "redirect:/user/main";
			} else {
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
		dto.setEmail(request.getParameter("emailBody") + "@" + request.getParameter("emailDomain"));
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

	/*
	 * 카카오 로그인 요청 회원 가입 이력이 있으면 세션에 User정보 입력 후 main page redirect 첫 로그인 이라면 추가 정보
	 * 입력 폼으로 이동
	 */
	@GetMapping("/kakao")
	public String getKakao(@RequestParam(name = "code") String code, Model model) throws ParseException {

		RestTemplate rt1 = new RestTemplate();

		HttpHeaders header1 = new HttpHeaders();
		header1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<>();
		params1.add("grant_type", "authorization_code");
		params1.add("client_id", kakaoClientId);
		params1.add("redirect_uri", "http://localhost:8080/user/kakao");
		params1.add("code", code);
		HttpEntity<MultiValueMap<String, String>> reqKakaoMessage = new HttpEntity<>(params1, header1);
		ResponseEntity<OAuthToken> response1 = rt1.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				reqKakaoMessage, OAuthToken.class);

		RestTemplate rt2 = new RestTemplate();

		HttpHeaders headers2 = new HttpHeaders();

		headers2.add("Authorization", "Bearer " + response1.getBody().getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> reqKakaoInfoMessage = new HttpEntity<>(headers2);

		ResponseEntity<KakaoProfile> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				reqKakaoInfoMessage, KakaoProfile.class);

		KakaoProfile kakaoProfile = response2.getBody();
		User oldUser = userService.searchId("kakao_auth" + String.valueOf(kakaoProfile.getId()));
		if (oldUser == null) {
			// 사용자가 최초로 소셜 로그인을 하는 사람
			SignUpDTO dto = new SignUpDTO();
			dto.setId("kakao_auth" + String.valueOf(kakaoProfile.getId()));
			dto.setUsername(kakaoProfile.getProperties().getNickname() + "#" + (random.nextInt(9000) + 1000));
			model.addAttribute("dto", dto);
			return "sign/socialSignUp";
		}

		session.setAttribute("principal", oldUser);
		return "redirect:/user/main";
	}

	/*
	 * 최초 소셜 로그인시 추가 정보 입력 후 회원가입 요청 시 동작 부분
	 */
	@Transactional
	@PostMapping("/social-sign-up")
	public String kakaoSignUp(SignUpDTO dto, Model model, HttpServletRequest request) {
		try {
			dto.setPassword(tencoKey);
			dto.setNickname(request.getParameter("nickname"));
			dto.setBirthDate(request.getParameter("birthDate"));
			dto.setEmail(request.getParameter("emailBody") + "@" + request.getParameter("emailDomain"));
			dto.setPhoneNumber(request.getParameter("phoneNumber"));
			userService.createUser(dto);
			request.setAttribute("msg", "간편 회원가입 완료");
			request.setAttribute("url", "sign-in");
			return "alert";
		} catch (Exception e) {
			throw new DataDeleveryException("회원가입에 실패하였습니다 올바른 정보를 입력해 주세요.", HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * 네이버 로그인 - 카카오 로그인과 거의 상동
	 */
	@GetMapping("/naver")
	public String getNaverLogin(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,
			Model model, HttpServletRequest request) throws UnsupportedEncodingException {

		String redirectURI = URLEncoder.encode("http://localhost:8080/user/naver", "UTF-8");
		String apiURL = "https://nid.naver.com/oauth2.0/token";

		// 첫 번째 RestTemplate 호출: 네이버 로그인 토큰 요청
		RestTemplate rt1 = new RestTemplate();
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<>();
		params1.add("grant_type", "authorization_code");
		params1.add("client_id", naverClientId);
		params1.add("client_secret", naverClientSecret);
		params1.add("redirect_uri", redirectURI);
		params1.add("code", code);
		params1.add("state", state);

		HttpEntity<MultiValueMap<String, String>> reqNaverMessage = new HttpEntity<>(params1, headers1);
		ResponseEntity<NaverOauthToken> response1 = rt1.exchange(apiURL, HttpMethod.POST, reqNaverMessage,
				NaverOauthToken.class);

		// 두 번째 RestTemplate 호출: 네이버 사용자 정보 요청
		RestTemplate rt2 = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + response1.getBody().getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> reqNaverInfoMessage = new HttpEntity<>(headers2);
		ResponseEntity<NaverProfile> response2 = rt2.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.POST,
				reqNaverInfoMessage, NaverProfile.class);

		NaverProfile naverProfile = response2.getBody();
		User oldUser = userService.searchId("naver_auth" + naverProfile.getResponse().getNickname());
		if (oldUser == null) {
			SignUpDTO dto = new SignUpDTO();
			dto.setId("naver_auth" + naverProfile.getResponse().getNickname());
			dto.setUsername(naverProfile.getResponse().getNickname() + "#" + (random.nextInt(9000) + 1000));
			model.addAttribute("dto", dto);
			return "sign/socialSignUp";
		}
		session.setAttribute("principal", oldUser);
		return "redirect:/user/main";
	}

	@GetMapping("/google")
	public String getGoogleLogin(@RequestParam(name = "code") String code, Model model) throws ParseException {

		RestTemplate rt1 = new RestTemplate();

		HttpHeaders header1 = new HttpHeaders();
		header1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params1 = new LinkedMultiValueMap<>();
		params1.add("grant_type", "authorization_code");
		params1.add("client_id", googleClientId);
		params1.add("client_secret", googleClientSecret);
		params1.add("redirect_uri", "http://localhost:8080/user/google");
		params1.add("code", code);
		HttpEntity<MultiValueMap<String, String>> reqGoogleMessage = new HttpEntity<>(params1, header1);
		ResponseEntity<GoogleOauthToken> response1 = rt1.exchange("https://oauth2.googleapis.com/token",
				HttpMethod.POST, reqGoogleMessage, GoogleOauthToken.class);

		RestTemplate rt2 = new RestTemplate();

		HttpHeaders headers2 = new HttpHeaders();

		headers2.add("Authorization", "Bearer " + response1.getBody().getAccessToken());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> reqGoogleInfoMessage = new HttpEntity<>(headers2);

		ResponseEntity<GoogleProfile> response2 = rt2.exchange("https://www.googleapis.com/oauth2/v3/userinfo",
				HttpMethod.POST, reqGoogleInfoMessage, GoogleProfile.class);

		GoogleProfile googleProfile = response2.getBody();
		User oldUser = userService.searchId("google_auth" + String.valueOf(googleProfile.getName()));
		if (oldUser == null) {
			// 사용자가 최초로 소셜 로그인을 하는 사람
			SignUpDTO dto = new SignUpDTO();
			dto.setId("google_auth" + String.valueOf(googleProfile.getName()));
			dto.setUsername(googleProfile.getName() + "#" + (random.nextInt(9000) + 1000));
			model.addAttribute("dto", dto);
			return "sign/socialSignUp";
		}

		session.setAttribute("principal", oldUser);
		return "redirect:/user/main";
	}

	@GetMapping("/alarm")
	@ResponseBody
	public String getAlarmList() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		User user = (User) session.getAttribute("principal");
		List<Alarm> alarmList = null;
		List<AlarmDTO> alarmDTO = new ArrayList<>();
		alarmList = alarmService.findAlarmAll(user.getUserId());
		for (Alarm alarm : alarmList) {
			User opponent = userService.searchByUserId(alarm.getOpponentId());
			AlarmDTO dto = AlarmDTO.builder().type(alarm.getType()).id(alarm.getId()).status(alarm.getStatus())
					.userId(opponent.getUserId()).nickname(opponent.getNickname())
					.uploadFileName(opponent.getUploadFileName()).content(alarm.getContent()).build();
			alarmDTO.add(dto);
		}
		String alarmJSON = objectMapper.writeValueAsString(alarmDTO);
		System.out.println(alarmJSON);
		return alarmJSON;
	}

	@GetMapping("move")
	public String alarmMoveHandler(@RequestParam(name = "type") int type, @RequestParam(name = "userId") int userId) {
		if (type == 1) {
			return "redirect:/chat/friendChat?id=" + userId;
		} else if (type == 2) {
			return "redirect:/friends/wait";
		} else if (type == 3) {
			// 커뮤니티 관련 알림 이동 처리
		} else if (type == 4) {
			// 강의 관련 알림 이동 처리
		}
		return "redirect:/user/main";
	}

	@PostMapping("status")
	@ResponseBody
	public String alarmStatusHandler(@RequestBody List<AlarmDTO> alarmList) {
		List<Integer> alarmIdList = new ArrayList<>();
		for (AlarmDTO alarms : alarmList) {
			alarmIdList.add(alarms.getId());
		}
		if(!alarmIdList.isEmpty()) {
			System.out.println("알림값 변경!!");
			alarmService.changeStatusBatch(alarmIdList);
		}
		return "ok";
	}
	
	@PostMapping("deleteAlarm")
	@ResponseBody
	public String postMethodName(@RequestBody List<Integer> alarmId) {
		List<Integer>deleteList = alarmId;
		alarmService.deleteAlarm(deleteList);
		return "ok";
	}
	
	@GetMapping("deleteFriend")
	@ResponseBody
	public String postMethodName(@RequestParam(name="userId")int id) {
		User user = (User)session.getAttribute("principal");
		int result = 0;
		result = friendService.removeFriend(user.getUserId(), id);
		if(result != 0) {
			return "ok";
		} else {
			return "fail";
		}
	}
	
	// git push protection error debug

}
