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
import com.example.demo.repository.model.Board;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.Comment;
import com.example.demo.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/support")
@RequiredArgsConstructor
public class AdminSupportController {
	
	@Autowired
	private final AdminService adminService;
		
	@GetMapping("")
	public String adminCommunityPage(Model model){
//		List<Category> categoryList = adminService.selectAllCategory();
//		List<Board> boardList = adminService.selectAllBoard();
//		model.addAttribute("categoryList",categoryList);
//		model.addAttribute("boardList", boardList);		
//		
		return "admin/adminSupport"; 
	}
	
	
	
}
