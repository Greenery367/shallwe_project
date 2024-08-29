package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.BoardCreateDTO;
import com.example.demo.repository.model.Board;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/community")
public class BoardController {
	
	private final BoardService boardService;
	private final HttpSession httpSession;
	
	@Autowired
	public BoardController(BoardService boardService, HttpSession httpSession) {
		this.boardService = boardService;
		this.httpSession = httpSession;
	}
	
	// 커뮤니티에서 전체 게시글을 보여주는 메소드
	@GetMapping("/")
	public String boardList(Model model) {
	    List<Board> boards = boardService.findAll();
	    model.addAttribute("boards", boards);
	    return "community/list";
	}
	
	// 카테고리별 이동
	@GetMapping("/category/{categoryId}")
    public String getBoardByCategory(@PathVariable("categoryId") Integer categoryId,
    		@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
    		Model model) {
		
		int totalBoardNum = boardService.findByCategoryTotalBoard(categoryId);
		int limit=10;
		int calculatedOffset = limit*(currentPage -1);
		int totalPage = (int) Math.ceil((double) totalBoardNum / limit);
        List<Board> boardList = boardService.getBoardByCategroy(categoryId, limit, calculatedOffset);
        model.addAttribute("boardList", boardList);
        System.out.println("boardList :: " + boardList);
        model.addAttribute("limit", limit);
		System.out.println("limit : " + limit);
		model.addAttribute("totalPage", totalPage);
		System.out.println("totalPage : " + totalPage);
		model.addAttribute("calculatedOffset", calculatedOffset);
		System.out.println("calculatedOffset : " + calculatedOffset);
		model.addAttribute("currentPage", currentPage); // currentPage 추가
        return "community/list"; 
    }

	
	// 글 수정 이동
	@GetMapping("/updateBoard/")
	public String boardUpdate() {
		return "community/updateBoard";
	}
	
	// 글 상세보기 이동
	@GetMapping("/boardDetail/{id}")
	public String boardDetail(@PathVariable("id") Integer id,
			 @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
			Model model) {
		boardService.increaseViewNum(id);
	    Board board = boardService.readBoardDetail(id);
	    model.addAttribute("board", board);
	    model.addAttribute("currentPage", currentPage); // 현재 페이지 정보 추가
	    return "/community/boardDetail";
	}
	
	// 게시글 작성 페이지 이동
    @GetMapping("/createBoard")
    public String createBoardForm(
    		@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
    		Model model) {
        model.addAttribute("board", new Board());
        model.addAttribute("currentPage", currentPage); // 현재 페이지 정보 추가
        return "community/createBoard";
    }
    
    // 게시글 작성 기능
    @PostMapping("/createBoard")
    public String createBoard(BoardCreateDTO boardCreateDTO) {
//    	Principal principal) { // 현재 로그인한 사용자 정보
//
//    	    Integer authorId = getCurrentUserId(principal); // 현재 로그인한 사용자의 ID를 가져오는 메소드
    	
    	
        boardService.createBoard(boardCreateDTO);
        return "redirect:/community/";
    }
    
 // 게시글 수정 페이지 이동
    @GetMapping("/updateBoard/{id}")
    public String updateBoardForm(
    		@PathVariable("id") Integer id,
    		Model model) {
    	System.out.println("아이디다!!" + id);
    	Board board = boardService.readBoardDetail(id);
        model.addAttribute("board", board);
        return "community/updateBoard";
    }
    
    
    // 수정된거 넘기기
    @PostMapping("/update/{id}")
    public String updateBoard(Model model, Board board, @PathVariable("id") Integer id) {
    	boardService.updateBoard(board.getId(), board.getTitle(), board.getContent(), board.getAuthorId());
        Board newBoard = boardService.readBoardDetail(id);
	    model.addAttribute("board", newBoard);
        return "redirect:/community/boardDetail/" + id; // 수정 후 상세 페이지로 이동
    }
	
	
	
	
}
