package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.model.User;
import com.example.demo.service.RegisterExchangeService;
import com.example.demo.service.RegisterSubmallService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/my-page/exchange-list")
@RequiredArgsConstructor
public class UserExchangeController {
	
	private final RegisterExchangeService registerExchangeService;
	private final RegisterSubmallService registerSubmallService;
	private final UserService userService;
	private final HttpSession httpSession;
	
	
	@GetMapping ("/{userId}")
	public String exchangePage (@PathVariable("userId") Integer userId,
								Model model,
								 HttpSession httpSession) {
			System.out.println("호에에에");
		    User user = (User) httpSession.getAttribute("principal");  // 세션에서 유저 가져오기
		    if (user == null) {
		    	return "redirect:/user/sign-in";
		    	
		    }
		    if (user != null) {
		        long lectureMoney = userService.getLectureCash(user.getUserId());  // 렉처머니 조회

		        if (lectureMoney >= 50000) {  // 렉처머니 5만원 이상인지 확인
		        	boolean registerSubMall = registerSubmallService.isSubmallRegistered(user.getUserId());
		           // boolean hasSubMallRequest = registerSubmallService.hasSubMallRequest(user.getId());

		            if (registerSubMall ) {  // 섭몰과 신청 둘 다 있으면 환전 내역 페이지로 이동
		                return "exchangeHistory";
		            } else if (!registerSubMall ) {  // 신청만 있으면 대기 상태 페이지로 이동
		                return "subMallRequestPending";
		            } else {  // 신청도 없으면 신청 페이지로 이동
		                return "subMallApply";
		            }
		        } else {
		            return "errorPage";  // 렉처머니 부족 시 에러 페이지
		        }
		    }
		    
		    return "/user/sign-in";  // 유저 없으면 로그인 페이지로 이동
		
		// 1. 세션서 유저 가옴 
		// 2. 유저의 렉처머니 5만원 이상인가 판단 
		// 3. 만약 5만 이상이면 서브몰 신청과 서브몰이 있는가 ? 
		// 4. 둘 다 있으면 환전 내역 (신청) 페이지로 가고 신청만 있으면 대기상태로 ㄱㄱ 
		// 5. 아에 없으면 서브몰 신청 페이지로 넘어감 
		
		// httpSesiion = 유저
		/**
		 * if(user!=null){
		 * int lm = userservice.getlecturmoney
		 * 	if(렉머>=50000){
		 * 		DB에서 섭몰, 섭몰신청을 찾아봄
		 * 			if(섭몰ㅇ 섭몰신청ㅇ){
		 * 				현재 렉머 + 환전신청내역
		 * 			} else if(섭몰x 섭몰 신청ㅇ){
		 * 				섭몰 신청 상태 = 대기
		 * 			} else {
		 * 				섭몰 만들어 => 섭몰 신청 페이지}
		 * 		} else{
		 * 			너 ㅂㅂ 
		 * }
		 * 
		 * 
		 * 
		 * 
		 */
		
		
	}
	
	
	
}
