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
import com.example.demo.repository.model.RegisterSubmall;
import com.example.demo.repository.model.Submall;
import com.example.demo.repository.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.RefundService;
import com.example.demo.service.RegisterSubmallService;
import com.example.demo.service.SubmallService;
import com.example.demo.service.UserService;


@Controller
@RequestMapping("/admin/submall")
@RequiredArgsConstructor
public class AdminSubmallController {

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private SubmallService submallService;
	
	@Autowired
	private RegisterSubmallService registerSubmallService;
	
	@Autowired
	private UserService userService;

	/**
	 * 서브몰 관리 페이지 접속
	 * @param model
	 * @return
	 */
	// http://localhost:8080/admin/submall/list
	@GetMapping("/list")
	public String submallBoardPage(Model model) {
		
		List<RegisterSubmall> submallRegisterList = submallService.getllAllSubmallRegitser(10, 0);
		List<Submall> submallList = submallService.getllAllSubmall(10, 0);
		List<SubmallInfoDTO> submallInfoList = new ArrayList<>();
		
		// 서브몰 정보 리스트 생성
		for(int i=0; i<submallRegisterList.size(); i++) {
			Integer id = submallRegisterList.get(i).getUserId();
			User user = userService.searchByUserId(id); // 유저 정보
			BankDTO bankInfo = submallService.getBankInfoById(id); // 계좌 정보
			submallInfoList.add(SubmallInfoDTO.builder().submallInfo(submallRegisterList.get(i)).user(user).bankInfo(bankInfo).build());
		}
		
		model.addAttribute("submallInfoList", submallInfoList);
		model.addAttribute("submallList", submallList);
		System.out.println("서브몰!!!!!!!!!"+submallList);
		return "/admin/adminSubmall";
	}
	
	/**
	 * 서브몰 생성하기
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	// 서브몰 생성 요청하기
	// http://localhost:8080/admin/submall/add
	@PostMapping("/add")
	@ResponseBody
	public String addSubmall(@RequestBody IdDTO id) throws IOException, InterruptedException {
		
		RegisterSubmall registerInfo = registerSubmallService.selectRegisterSubmallInfoById(Integer.parseInt(id.id));
		User user = userService.searchByUserId(registerInfo.userId); // 유저 정보
		BankDTO bankInfo = submallService.getBankInfoById(registerInfo.getBankId()); // 계좌 정보
		
		if(submallService.makeNewSubmall(registerInfo,user,bankInfo)) {
			System.out.println("서브몰 신청 완료!!!!!!!");
		} else {
			return "/test/main";
		}
		
		
		return "/admin/adminSubmall";
	}
	
	
	
}
