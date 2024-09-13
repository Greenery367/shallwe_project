package com.example.demo.controller;

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
import com.example.demo.dto.RefundResponseDTO;
import com.example.demo.dto.SubmallInfoDTO;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.Refund;
import com.example.demo.repository.model.RegisterSubmall;
import com.example.demo.repository.model.Submall;
import com.example.demo.repository.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.RefundService;
import com.example.demo.service.SubmallService;
import com.example.demo.service.UserService;


@Controller
@RequestMapping("/admin/exchange")
@RequiredArgsConstructor
public class AdminExchangeController {

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private SubmallService submallService;
	@Autowired
	private UserService userService;

	/**
	 * 서브몰 관리 페이지 접속
	 * @param model
	 * @return
	 */
	// http://localhost:8080/admin/exchange/submall
	@GetMapping("/submall")
	public String submallBoardPage(Model model) {
		
		List<RegisterSubmall> submallRegisterList = submallService.getllAllSubmallRegitser(10, 0);
		List<SubmallInfoDTO> submallInfoList = new ArrayList<>();
		
		for(int i=0; i<submallRegisterList.size(); i++) {
			Integer id = submallRegisterList.get(i).getUserId();
			User user = userService.searchByUserId(id); // 유저 정보
			BankDTO bankInfo = submallService.getBankInfoById(id); // 계좌 정보
			submallInfoList.add(SubmallInfoDTO.builder().submallInfo(submallRegisterList.get(i)).user(user).bankInfo(bankInfo).build());
		}
		
		model.addAttribute("submallInfoList", submallInfoList);
		
		for(int i=0; i<submallInfoList.size(); i++) {
			System.out.println(submallInfoList.get(i));
		}
		return "/admin/adminSubmall";
	}
	
	// 서브몰 생성 요청하기
	@PostMapping("/add-submall")
	public String addSubmall(@RequestParam("submallInfo")SubmallInfoDTO submallInfo) {
		System.out.println("하하하");
		System.out.println("submallInfo"+submallInfo);
		Submall newSubmall = submallService.makeNewSubmall(submallInfo);
		
		return newSubmall.toString();
	}
	
	
	
}
