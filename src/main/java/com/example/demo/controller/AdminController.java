package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.AdminService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	private final HttpSession session;
	@Autowired
	private final AdminService adminService;
	
	@GetMapping({"/", "/dashboard"})
	public String adminMainPage(Model model) {
		
		int numberOfUser = adminService.countUser();
		int numberOfChargeCash = adminService.countChargeCash();
		double cashUseRate = adminService.countSpendCashRate();
		
		model.addAttribute("numberOfUser", numberOfUser);
		model.addAttribute("numberOfChargeCash", numberOfChargeCash);
		model.addAttribute("cashUseRate", cashUseRate);
		return "admin/adminMain";
	}
	
	
}
