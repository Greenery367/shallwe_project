package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.model.CashHistory;
import com.example.demo.service.CashHistroyService;


@Controller
@RequestMapping("/admin/refund")
@RequiredArgsConstructor
public class AdminRefundController {

	@Autowired
	private HttpSession httpSession;
	@Autowired
	private CashHistroyService cashHistoryService;
	
	
	// 환불 페이지 접속
	@GetMapping("/")
	public String refundBoardPage() {
		List<CashHistory> cashHistoryBoard = cashHistoryService.getAllCashHistroy();
		return "admin/adminRefund";
	}
	
	// 환불 페이지 처리
	
	
	
}
