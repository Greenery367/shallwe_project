package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.IdDTO;
import com.example.demo.repository.model.RegisterExchange;
import com.example.demo.repository.model.User;
import com.example.demo.service.RegisterExchangeService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/admin/exchange")
@RequiredArgsConstructor
public class AdminExchangeController {

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegisterExchangeService registerExchangeService;

	/**
	 * 환전 관리 페이지 접속
	 * @param model
	 * @return
	 */
	// http://localhost:8080/admin/exchange/list
	@GetMapping("/list")
	public String exchangeBoardList(Model model) {
		
		List<RegisterExchange> exchangeList = registerExchangeService.getAllExchangeList(10,0);
		model.addAttribute("exchangeList", exchangeList);
		
		return "/admin/adminExchange";
	}
	
	/**
	 * 환전 신청 처리
	 * @param id
	 * @return
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@ResponseBody
	@PostMapping("/send-request")
	public String sendExchangeRequest(@RequestBody IdDTO id, Model model) throws IOException, InterruptedException {
		System.out.println("1111111111");
		
		// 1. 환전 신청 id 찾기
		int exchangeId = Integer.parseInt(id.getId());
		System.out.println("222222222----------"+exchangeId);
		
		// 2. DB에서 환전 신청 기록 찾기
		RegisterExchange exchageRecord = registerExchangeService.getRegisterExchange(exchangeId);
		System.out.println("333333333----------"+exchageRecord);
		
		// 3. 해당 유저 잔액 - 환전 금액 대조
		// 유저의 현재 잔액 > 신청 금액 이어야 한다.
		User user = userService.searchByUserId(exchageRecord.userId);
		if(user.getCurrentCash()<exchageRecord.getAmount()) {
			 // 환불 불가-> 스테이터스 변경
		}
		
		// 4. 셸위 잔액 조회
		// Boolean flag = registerExchangeService.sendRequestExchange(exchageRecord);
		// System.out.println("4444444----------------");
		boolean flag = true;
		
		// 5. 환전 처리
		// (1) 만약 환전이 가능하다면? (잔액 > 환전 요청액)
		if(flag == true) {
			// 환전 요청
			registerExchangeService.sendExchangeRequest(exchageRecord);
			// status 설정 - 환전 처리 중
			registerExchangeService.setStatusToFinished(exchageRecord);
		}
		
		
		List<RegisterExchange> exchangeList = registerExchangeService.getAllExchangeList(10,0);
		model.addAttribute("exchangeList", exchangeList);
		
		return "/admin/adminExchange";
	}
	
}