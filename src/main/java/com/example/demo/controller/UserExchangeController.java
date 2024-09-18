package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.model.RegisterSubmall;
import com.example.demo.repository.model.Submall;
import com.example.demo.repository.model.User;
import com.example.demo.service.BankService;
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
	private final BankService bankService;
	private final HttpSession httpSession;

	@GetMapping("/")
	public String exchangePage(Model model, HttpSession httpSession) {
		System.out.println("호에에에");
		User user = (User) httpSession.getAttribute("principal"); // 세션에서 유저 가져오기
		if (user == null) {
			return "redirect:/user/sign-in";

		} else {
			long lectureMoney = userService.getLectureCash(user.getUserId()); // 렉처머니 조회

			boolean registerSubMall = registerSubmallService.isSubmallRegistered(user.getUserId());
			System.out.println("-------------" + registerSubMall);
			Submall isSubMall = registerSubmallService.isSubmallId(user.getUserId());
			System.out.println("++++++++++++++++++++" + isSubMall);
			if (lectureMoney >= 50000 || isSubMall != null ) { // 렉처머니 5만원 이상인지 확인
				
				if (registerSubMall == true && isSubMall != null) { // 섭몰과 신청 둘 다 있으면 환전 내역 페이지로 이동
					return "/myPage/userExchange/exchangeRecord";
				} else if (registerSubMall == true && isSubMall == null) { // 신청만 있으면 대기 상태 페이지로 이동
					return "/myPage/userExchange/responseSubmall";
				} else { // 신청도 없으면 신청 페이지로 이동
					model.addAttribute("banks", bankService.getAllBanks()); // 은행 목록 추가
					return "/myPage/userExchange/resiterSubmall";
				}
			} else {
				return "/myPage/userExchange/denyLecture"; // 렉처머니 부족 시 에러 페이지
			}
		}

		

	}

}
