package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.Advertise;
import com.example.demo.dto.CreateAdvertiseDTO;
import com.example.demo.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/advertise")
@RequiredArgsConstructor
public class AdminAdvertiseController {

	@Autowired
	private final AdminService adminService;
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@GetMapping("")
	public String adminAdvertisementPage(Model model) {
		
		List<Advertise> advertiseList = adminService.selectAllAdvertise();
		model.addAttribute("advertiseList", advertiseList);		
		
		return "admin/adminAdvertisement";
	}
	
	// 광고 추가 요청
	@PostMapping("/insert-advertise")
	public String insertAdvertiseProc(CreateAdvertiseDTO dto) {
	    // 광고 추가 서비스 호출
	   adminService.insertAdvertise(dto);

	    return "redirect:/admin/advertise";
	}
	
	// 광고 수정 요청
	@PostMapping("/update-advertise")
	public String updateAdvertiseProc(Advertise advertise) {
		
		adminService.updateAdvertise(advertise);
		
		return "redirect:/admin/advertise";
	}
	
	// 광고 삭제 요청
	@PostMapping("/delete-advertise")
	public String deleteAdvertiseProc(Advertise advertise) {
		adminService.deleteAdvertise(advertise);
		
		return "redirect:/admin/advertise";
	}
	
	
	
	
}
