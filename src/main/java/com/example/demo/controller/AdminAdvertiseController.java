package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CreateAdvertiseDTO;
import com.example.demo.repository.model.Advertise;
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
	
	@Value("${file.advertise-dir}")
	private String advertiseDir;
	
	@GetMapping("")
	public String adminAdvertisementPage(Model model) {
		
		List<Advertise> advertiseList = adminService.selectAllAdvertise();
		List<Advertise> advertiseListNow = adminService.selectAdvertiseNow();
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		model.addAttribute("advertiseList", advertiseList);		
		model.addAttribute("advertiseListNow",advertiseListNow);
		model.addAttribute("advertiseListOne", advertiseListOne);
		model.addAttribute("advertiseListTwo", advertiseListTwo);
		model.addAttribute("advertiseListThree", advertiseListThree);
		return "admin/adminAdvertisement"; 
	}
	
	// 광고 추가 요청
	@PostMapping("/insert-advertise")
	public String insertAdvertiseProc(@RequestParam("placeId") Integer placeId,
	                                   @RequestParam("title") String title,
	                                   @RequestParam("customer") String customer,
	                                   @RequestParam("link") String link,
	                                   @RequestParam("file") MultipartFile file,
	                                   @RequestParam("startDate") String startDate,
	                                   @RequestParam("endDate") String endDate,
	                                   @RequestParam("status") Integer status
	                                  ) throws IOException {
	    CreateAdvertiseDTO dto = new CreateAdvertiseDTO();
	    
	    dto.setPlaceId(placeId);
	    dto.setTitle(title);
	    dto.setCustomer(customer);
	    dto.setLink(link);
	    dto.setStartDate(startDate);
	    dto.setEndDate(endDate);
	    dto.setStatus(status);
	    
	    if (file != null && !file.isEmpty()) {
	        String originFilename = file.getOriginalFilename();
	        String uuid = UUID.randomUUID().toString();
	        String uploadFileName = uuid + "_" + originFilename;
	        
	        dto.setOriginFileName(originFilename);
	        dto.setUploadFileName(uploadFileName);
	        
	        File targetFile = new File(advertiseDir + File.separator + uploadFileName);
	        targetFile.getParentFile().mkdirs();
	        file.transferTo(targetFile);
	    }
	    
	    try {
	        adminService.insertAdvertise(dto);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
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