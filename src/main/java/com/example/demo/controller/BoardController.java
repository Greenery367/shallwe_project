package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.BoardService;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 커뮤니티에서 전체 게시글을 보여주는 메소드
	@GetMapping("/")
	public String boardPage() {
		return "board/list";
	}
	
	
	
	
}
