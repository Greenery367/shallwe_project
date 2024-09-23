package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.AdminSelectCommentDTO;
import com.example.demo.dto.CreateCategoryDTO;
import com.example.demo.dto.FrequeDTO;
import com.example.demo.repository.model.Board;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.Comment;
import com.example.demo.service.AdminService;
import com.example.demo.service.CsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/support")
@RequiredArgsConstructor
public class AdminSupportController {
	
	@Autowired
	private final AdminService adminService;
	
	@Autowired
	private final CsService csService;
	@Autowired
	private final HttpSession session;
		
	@GetMapping("")
	public String adminCommunityPage(Model model, HttpServletRequest httpServletRequest,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "siez", defaultValue = "10") int size){
			
			int totalRecords = csService.countFreq();
			int totalPages = (int) Math.ceil((double) totalRecords / size);
			List<FrequeDTO> postList = csService.readAllFreq(page, size);
			model.addAttribute("postList", postList);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("curruntPage", page);
			model.addAttribute("size", size);
			httpServletRequest.setAttribute("totalPages", totalPages);
			httpServletRequest.setAttribute("curruntPage", page);
		
		return "admin/adminSupport"; 
	}
	
	
	
}
