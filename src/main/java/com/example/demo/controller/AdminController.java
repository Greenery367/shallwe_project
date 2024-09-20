package com.example.demo.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.CashChargeGraphVO;
import com.example.demo.repository.model.Admin;
import com.example.demo.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	private final HttpSession session;
	@Autowired
	private final AdminService adminService;
	
	
	@GetMapping("")
	public String adminMainPage( Model model) throws Exception {
		
	    // 대시보드 통계를 위한 계산
	    int numberOfUser = adminService.countUser();
	    int numberOfChargeCash = adminService.countChargeCash();
	    double cashUseRate = adminService.countSpendCashRate();
	    int cashChargeAmount = adminService.countChargeAmountOneDay(null);
	    List<CashChargeGraphVO> cashChargeData = adminService.countChargeAmountAllDay();
	    
	    List<Integer> amountList = new ArrayList<>();
	    List<String> dateList = new ArrayList<>();
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
	    
//	    for (CashChargeGraphVO data : cashChargeData) {
//	    	String a = String.valueOf(data.getCreatedAt());
//	    	String realA = a.substring(5,7);
//	    	String realB = a.substring(8,10);
//	    	String RealDate = realA+"."+realB;
//	    	dateList.add(RealDate);
//	    }
	    
	    for (CashChargeGraphVO data : cashChargeData) {
	    	String a = String.valueOf(data.getCreatedAt());
	    	amountList.add(data.getAmount());
	    	dateList.add(a);
	    }
	    
	    // JSON으로 변환
	    
	    System.out.println("----------"+amountList);
	    System.out.println("----------"+dateList);
	    
	    // 모델에 통계 데이터 추가
	    model.addAttribute("numberOfUser", numberOfUser);
	    model.addAttribute("numberOfChargeCash", numberOfChargeCash);
	    model.addAttribute("cashUseRate", cashUseRate);
	    model.addAttribute("cashChargeAmount", cashChargeAmount);
	    model.addAttribute("amountList", amountList);
	    model.addAttribute("dateList", dateList);

	    // 기본적으로 HTML 페이지를 반환
	    return "admin/adminMain";
	}
	
	// 차트를 불러오기 위한 요청
	@GetMapping("/dashboard")
	@ResponseBody
	public List<CashChargeGraphVO> getDashboardData(@RequestParam(value = "createdAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String createdAtStr) throws Exception {
	    Timestamp createdAt = null;
	    if (createdAtStr != null && !createdAtStr.isEmpty()) {
	        createdAt = Timestamp.valueOf(createdAtStr);
	    }

	    List<CashChargeGraphVO> chargeGraphVO = adminService.countChargeAmountAllDay();
	    
	    return chargeGraphVO; // 이 데이터가 JSON으로 반환됨
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
