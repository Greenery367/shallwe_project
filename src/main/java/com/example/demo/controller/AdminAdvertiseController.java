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
	
	@PostMapping("/insertAdvertise")
	public String insertAdvertiseProc(Model model ) {
		adminService.insertAdvertise();
		
		return "admin/adminAdvertisement";
	}
	
	
	
}
