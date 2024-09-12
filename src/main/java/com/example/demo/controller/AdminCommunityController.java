package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/admin/community")
@RequiredArgsConstructor
public class AdminCommunityController {
	
	@Autowired
	private final AdminService adminService;
		
	@GetMapping("")
	public String adminCommunityPage(Model model){
		List<Category> categoryList = adminService.selectAllCategory();
		List<Board> boardList = adminService.selectAllBoard();
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("boardList", boardList);		
		
		return "admin/adminCommunity"; 
	}
	
	
	@GetMapping("/detail")
	public String adminCommunityDetailPage(Model model, Board board) {
		Board boardDetail = adminService.selectBoardById(board.getId());
		List<AdminSelectCommentDTO> boardCommentList = adminService.selectCommentByPostId(board.getId()); 
		System.out.println(boardCommentList);
		model.addAttribute("boardDetail",boardDetail);
		model.addAttribute("boardCommentList", boardCommentList);
		
		return "admin/adminCommunityDetail";
	}
	
	
	@PostMapping("/detail")
	public String adminCommunityDetailProc(Model model, Board board) {
		Board boardDetail = adminService.selectBoardById(board.getId());
		List<AdminSelectCommentDTO> boardCommentList = adminService.selectCommentByPostId(board.getId());
		model.addAttribute("boardDetail",boardDetail);
		model.addAttribute("boardCommentList", boardCommentList);
		
		return "admin/adminCommunityDetail";
	}
	
	
	// 카테고리 추가 요청
	@PostMapping("/insert-category")
	public String insertCategoryProc(@RequestParam("title") String title) {
		CreateCategoryDTO dto = new CreateCategoryDTO();
		
		dto.setTitle(title);
		adminService.insertCategory(dto);
		
		return "redirect:/admin/community";
	}
	
	// 카테고리 수정 요청
	@PostMapping("/update-category")
	public String updateCategoryProc(Category category) {
		adminService.updateCategory(category);
		
		return "redirect:/admin/community";
	}
	
	// 카테고리 삭제 요청
	@PostMapping("/delete-category")
	public String deleteCategoryProc(Category category) {
		adminService.deleteCategoryById(category);
		
		return "redirect:/admin/community";
	}
	
	// 게시글 수정 요청
	@PostMapping("/update-board")
	public String updateBoardProc(Board board) {
		adminService.updateBoard(board);

		return "redirect:/admin/community";
	}
	
	// 게시글 삭제 요청
	@PostMapping("/delete-board")
	public String deleteBoardProc(Board board) {
		adminService.deleteBoardById(board);
		return "redirect:/admin/community";
	}
	
	
}
