package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.BankDTO;
import com.example.demo.dto.IdDTO;
import com.example.demo.dto.RefundResponseDTO;
import com.example.demo.dto.RegisterSubmallDTO;
import com.example.demo.dto.SubmallInfoDTO;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.Refund;
import com.example.demo.repository.model.RegisterExchange;
import com.example.demo.repository.model.RegisterSubmall;
import com.example.demo.repository.model.Submall;
import com.example.demo.repository.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.RefundService;
import com.example.demo.service.RegisterExchangeService;
import com.example.demo.service.RegisterSubmallService;
import com.example.demo.service.SubmallService;
import com.example.demo.service.UserService;


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
	
	@ResponseBody
	@PostMapping("/send-request")
	public String sendExchangeRequest(@RequestBody IdDTO id) {
		System.out.println("1111111111");
		// 1. 환전 신청 id 찾기
		int exchangeId = Integer.parseInt(id.getId());
		System.out.println("222222222----------"+exchangeId);
		// 2. 환전 신청 기록 찾기
		RegisterExchange exchageRecord = registerExchangeService.getRegisterExchange(exchangeId);
		System.out.println("333333333----------"+exchageRecord);
		// 3. 해당 유저 잔액 - 환전 금액 대조
		
		// 3. 셸위 잔액 조회
		String result = registerExchangeService.sendRequestExchange(exchageRecord);
		System.out.println("4444444----------"+result);
		return null;
	}
	
	
}
