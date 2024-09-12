package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.model.Admin;
import com.example.demo.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
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
	
	
	@GetMapping({"", "/dashboard"})
	public String adminMainPage(Model model) {
		
		// 대시보드 통계를 위한 계산
		int numberOfUser = adminService.countUser();
		int numberOfChargeCash = adminService.countChargeCash();
		double cashUseRate = adminService.countSpendCashRate();
		
		model.addAttribute("numberOfUser", numberOfUser);
		model.addAttribute("numberOfChargeCash", numberOfChargeCash);
		model.addAttribute("cashUseRate", cashUseRate);
		return "admin/adminMain";
	}
	
	
	@GetMapping("sign-in")
	public String requestSignIn() {
		return "sign/adminSignIn";
	}
	
	/*
	 * 로그인 페이지 --> 로그인 요청
	 */
	@PostMapping("/sign-in")
	public String signIn(HttpServletRequest request) {
		String adminId = request.getParameter("id");
		if(adminId == null || adminId.trim().length() == 0) {
			request.setAttribute("msg", "ID를 입력하세요");
			request.setAttribute("url", "sign-in");
			return "alert";
		}
		String password = request.getParameter("password");
		if(password == null || password.trim().length() == 0) {
			request.setAttribute("msg", "Password를 입력하세요");
			request.setAttribute("url", "sign-in");
			return "alert";
		}
		Admin admin = adminService.searchId(adminId);
		if (admin == null) {
			request.setAttribute("msg", "존재하지 않는 ID입니다.");
			request.setAttribute("url", "sign-in");
			return "alert";
		} else {
			if (password.equals(admin.getPassword())) {
				session.setAttribute("principal", admin);
				return "redirect:/admin/dashboard";
			} else {
				request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
				request.setAttribute("url", "sign-in");
				return "alert";
			}
		}
	}
	
	
	
}
