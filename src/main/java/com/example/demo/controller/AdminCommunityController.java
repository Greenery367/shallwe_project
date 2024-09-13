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
	public String adminCommunityDetailPage(Model model, @RequestParam("id") String id) {
		System.out.println("*******************디테일 겟매핑******************");
		Board boardDetail = adminService.selectBoardById(Integer.parseInt(id));
		List<AdminSelectCommentDTO> boardCommentList = adminService.selectCommentByPostId(Integer.parseInt(id)); 
		System.out.println(boardCommentList);
		model.addAttribute("boardDetail",boardDetail);
		model.addAttribute("boardCommentList", boardCommentList);
		
		return "admin/adminCommunityDetail";
	}
	
	
	@PostMapping("/detail")
	public String adminCommunityDetailProc(Model model, Board board) {
		System.out.println("*******************디테일 포스트매핑******************");
		Board boardDetail = adminService.selectBoardById(board.getId());
		List<AdminSelectCommentDTO> boardCommentList = adminService.selectCommentByPostId(board.getId());
		model.addAttribute("boardDetail",boardDetail);
		model.addAttribute("boardCommentList", boardCommentList);
		
		return "redirect:/admin/community/detail";
	}
	
	
	// 카테고리 추가 요청
	@PostMapping("/insert-category")
	public String insertCategoryProc(@RequestParam("gameName") String gameName) {
		CreateCategoryDTO dto = new CreateCategoryDTO();
		
		dto.setGameName(gameName);
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
	
	// 댓글 삭제 요청
	@PostMapping("/detail/delete-comment")
	public String deleteCommentById(Model model, AdminSelectCommentDTO dto) {
		adminService.deleteCommentById(dto);
		System.out.println(dto);
		System.out.println("*******************" + dto.getPostId());
		return "redirect:/admin/community/detail?id=" + dto.getPostId();
	}
	
	
}
