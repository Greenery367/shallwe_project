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
			@RequestParam(name = "searchField", required = false) String searchField,
			@RequestParam(name = "searchValue", required = false) String searchValue, Model model) {

		int limit = 3;
		int calculatedOffset = limit * (currentPage - 1);
		List<Board> boardList;

		// 검색 필터에 따라 처리
		if ("content".equals(searchField) && searchValue != null && !searchValue.isEmpty()) {
			boardList = boardService.findByContent(categoryId, limit, calculatedOffset, searchValue);
		} else if ("title".equals(searchField) && searchValue != null && !searchValue.isEmpty()) {
			boardList = boardService.findByTitle(categoryId, limit, calculatedOffset, searchValue);
		} else if ("nickName".equals(searchField) && searchValue != null && !searchValue.isEmpty()) {
			boardList = boardService.findByNickName(categoryId, limit, calculatedOffset, searchValue);
		} else {
			boardList = boardService.getBoardByCategory(categoryId, limit, calculatedOffset);
		}

		int totalBoardNum = boardService.findByCategoryTotalBoard(categoryId);
		int totalPage = (int) Math.ceil((double) totalBoardNum / limit);

		model.addAttribute("boardList", boardList);
		model.addAttribute("limit", limit);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("calculatedOffset", calculatedOffset);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchValue", searchValue); // 검색어를 모델에 추가
		model.addAttribute("searchField", searchField); // 검색 필드를 모델에 추가

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
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage, Model model) {
		boardService.increaseViewNum(id);
		Board board = boardService.readBoardDetail(id);
		model.addAttribute("board", board);
		model.addAttribute("currentPage", currentPage); // 현재 페이지 정보 추가
		return "/community/boardDetail";
	}

	
	// 게시글 작성 페이지 이동
	// hint : 주소에서 카테고리 id를 동적으로 받아 넘겨야함 <-- 해라 꼭.. 
    @GetMapping("/createBoard")
    public String createBoardForm(@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
    		 					  @RequestParam(name = "categoryId", required = false, defaultValue = "0") Integer categoryId,
                                  Model model) {
        // 세션에서 작성자 ID 가져오기
//        Integer authorId = (Integer) httpSession.getAttribute("userId");
        Integer authorId = 1; // 임시 작성자 ID (예: 1)

        BoardCreateDTO boardCreateDTO = new BoardCreateDTO();
        boardCreateDTO.setAuthorId(authorId); // 작성자 ID 설정

        model.addAttribute("board", boardCreateDTO);
        model.addAttribute("currentPage", currentPage); // 현재 페이지 정보 추가
        model.addAttribute("authorId", authorId); // 작성자 ID를 모델에 추가
        model.addAttribute("categoryId", categoryId); // 카테고리 ID를 모델에 추가
        return "community/createBoard";
    }
	

 // 게시글 작성 기능
    @PostMapping("/createBoard")
    public String createBoard(BoardCreateDTO boardCreateDTO) {
        // 세션에서 작성자 ID 가져오기
//        Integer authorId = (Integer) httpSession.getAttribute("userId");
    	Integer authorId = 1; // 임시 작성자 ID (예: 1)
        boardCreateDTO.setAuthorId(authorId); // 작성자 ID 설정
        boardService.createBoard(boardCreateDTO);
        return "redirect:/community/"; // 게시글 목록으로 리다이렉트
    }

	// 게시글 수정 페이지 이동
	@GetMapping("/updateBoard/{id}")
	public String updateBoardForm(@PathVariable("id") Integer id, Model model) {
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
