package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.Advertise;
import com.example.demo.service.AdminService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/advertisement")
@RequiredArgsConstructor
public class AdminAdvertiseController {

	@Autowired
	private final HttpSession session;
	private final AdminService adminService;
	
	@GetMapping("")
	public String adminAdvertisementPage(Model model) {
		
		List<Advertise> advertiseList = adminService.selectAllAdvertise();
		model.addAttribute("advertiseList", advertiseList);		
		
		return "admin/adminAdvertisement";
	}
	
	// 광고 추가 요청
	@PostMapping("/insert-advertise")
	public String insertAdvertiseProc(Advertise advertise) {
		adminService.insertAdvertise(advertise);
		
		return "redirect:/admin/advertisement";
	}
	
	// 광고 수정 요청
	@PostMapping("/update-advertise")
	public String updateAdvertiseProc(Advertise advertise) {
		
		adminService.updateAdvertise(advertise);
		
		return "redirect:/admin/advertisement";
	}
	
	// 광고 삭제 요청
	@PostMapping("/delete-advertise")
	public String deleteAdvertiseProc(Advertise advertise) {
		adminService.deleteAdvertise(advertise);
		
		return "redirect:/admin/advertisement";
	}
	
	
	
}
