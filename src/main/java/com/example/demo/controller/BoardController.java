package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.BoardCreateDTO;
import com.example.demo.repository.model.Advertise;
import com.example.demo.repository.model.Board;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.Comment;
import com.example.demo.repository.model.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final CommentService commentService;
	private final AdminService adminService;
	private final HttpSession httpSession;

	// 커뮤니티에서 전체 게시글을 보여주는 메소드
	@GetMapping("/")
	public String boardList(Model model) {
		List<Board> boards = boardService.findAll();
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		model.addAttribute("advertiseListOne", advertiseListOne);
		model.addAttribute("advertiseListTwo", advertiseListTwo);
		model.addAttribute("advertiseListThree", advertiseListThree);
		model.addAttribute("categoryList", categoryList);

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
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		int totalBoardNum = 0;

		// 검색 필터에 따라 처리
		if ("content".equals(searchField) && searchValue != null && !searchValue.isEmpty()) {
			boardList = boardService.findByContent(categoryId, limit, calculatedOffset, searchValue);
			totalBoardNum = boardService.SearchContentTotalBoard(categoryId, searchValue);
		} else if ("title".equals(searchField) && searchValue != null && !searchValue.isEmpty()) {
			boardList = boardService.findByTitle(categoryId, limit, calculatedOffset, searchValue);
			totalBoardNum = boardService.SearchTitleTotalBoard(categoryId, searchValue);
		} else if ("nickName".equals(searchField) && searchValue != null && !searchValue.isEmpty()) {
			boardList = boardService.findByNickName(categoryId, limit, calculatedOffset, searchValue);
			totalBoardNum = boardService.SearchAuthorTotalBoard(categoryId, searchValue);
		} else {
			boardList = boardService.getBoardByCategory(categoryId, limit, calculatedOffset);
			totalBoardNum = boardService.findByCategoryTotalBoard(categoryId);
		}

		int totalPage = (int) Math.ceil((double) totalBoardNum / limit);
		currentPage = 1;

		String categoryName = boardService.getCategoryNameById(categoryId);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("advertiseListOne", advertiseListOne);
		model.addAttribute("advertiseListTwo", advertiseListTwo);
		model.addAttribute("advertiseListThree", advertiseListThree);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("boardList", boardList);
		model.addAttribute("limit", limit);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("calculatedOffset", calculatedOffset);
		model.addAttribute("categoryId", categoryId);
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
	@GetMapping("/board-detail/{id}")
	public String boardDetail(@PathVariable("id") Integer id,
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage, Model model) {
		boardService.increaseViewNum(id);
		Board board = boardService.readBoardDetail(id);
		List<Comment> comments = commentService.getCommentsByPostId(id); // 댓글 목록 조회
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		User user = (User) httpSession.getAttribute("principal");

		model.addAttribute("advertiseListOne", advertiseListOne);
		model.addAttribute("advertiseListTwo", advertiseListTwo);
		model.addAttribute("advertiseListThree", advertiseListThree);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("user", user);
		model.addAttribute("board", board);
		model.addAttribute("comments", comments); // 댓글 목록을 모델에 추가
		model.addAttribute("currentPage", currentPage); // 현재 페이지 정보 추가
		Integer authorId = board.getAuthorId(); // 또는 서비스에서 가져올 수 있음
		model.addAttribute("authorId", authorId);
		return "/community/boardDetail";
	}

	// 댓글 작성 기능
	@PostMapping("/createComment")
	public String createComment(Comment comment, Model model) {
		// 세션에서 작성자 ID 가져오기
		User user = (User) httpSession.getAttribute("principal");
		int id = user.getUserId();
		System.out.println("유저 네임 : " + user.getNickname());
		comment.setAuthorId(id); // 작성자 ID 설정

		commentService.createComment(comment);
		return "redirect:/community/board-detail/" + comment.getPostId(); // 게시글 목록으로 리다이렉트
	}

	// 댓글 수정 기능
	@PostMapping("/update-comment")
	public String updateComment(@RequestParam("commentId") Integer commentId, @RequestParam("content") String content,
			@RequestParam("postId") Integer postId) {
		User user = (User) httpSession.getAttribute("principal");
		Integer authorId = user.getUserId(); // 작성자 ID 가져오기
		commentService.updateComment(commentId, content, authorId, postId);
		return "redirect:/community/board-detail/" + postId; // 게시글 상세 페이지로 리다이렉트
	}

	// 댓글 삭제 기능
	@PostMapping("/delete-comment/{commentId}")
	public String deleteComment(@PathVariable("commentId") Integer commentId, @RequestParam("postId") Integer postId) {
		User user = (User) httpSession.getAttribute("principal");
		Integer authorId = user.getUserId(); // 작성자 ID 가져오기
		commentService.deleteComment(commentId, authorId, postId); // authorId와 postId 전달
		return "redirect:/community/board-detail/" + postId; // 게시글 상세 페이지로 리다이렉트
	}

	// 게시글 작성 페이지 이동
	// hint : 주소에서 카테고리 id를 동적으로 받아 넘겨야함 <-- 해라 꼭..
	@GetMapping("/create-board/{categoryId}")
	public String createBoardForm(@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
			@PathVariable("categoryId") Integer categoryId, Model model) {
		User user = (User) httpSession.getAttribute("principal");
		if (user == null) {
			return "redirect:/user/sign-in"; // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
		}

		BoardCreateDTO boardCreateDTO = new BoardCreateDTO();
		boardCreateDTO.setAuthorId(user.getUserId()); // 작성자 ID 설정
		boardCreateDTO.setCategoryId(categoryId); // 카테고리 ID 설정

		model.addAttribute("board", boardCreateDTO);
		model.addAttribute("currentPage", currentPage); // 현재 페이지 정보 추가
		model.addAttribute("authorId", user.getUserId());
		model.addAttribute("authorNickname", user.getNickname()); // 작성자 닉네임 추가
		model.addAttribute("categoryId", categoryId); // 카테고리 ID를 모델에 추가
		return "/community/createBoard";
	}

	// 게시글 작성 기능
	@PostMapping("/create-board")
	public String createBoard(BoardCreateDTO boardCreateDTO,
			@RequestParam(name = "categoryId", required = false) Integer categoryId, Model model) {
		// 세션에서 작성자 ID 가져오기
		User user = (User) httpSession.getAttribute("principal");
		boardCreateDTO.setCategoryId(categoryId);
		boardCreateDTO.setAuthorId(user.getUserId()); // 작성자 ID 설정
		model.addAttribute("categoryId", categoryId); // 카테고리 ID를 모델에 추가
		boardService.createBoard(boardCreateDTO);
		System.out.println("보드이이이이잉" + boardCreateDTO);
		return "redirect:/community/category/" + categoryId; // 게시글 목록으로 리다이렉트
	}

	// 게시글 수정 페이지 이동
	@GetMapping("/update-board/{id}")
	public String updateBoardForm(@PathVariable("id") Integer id, Model model) {
		System.out.println("아이디다!!" + id);
		User user = (User) httpSession.getAttribute("principal");

		Board board = boardService.readBoardDetail(id);
		model.addAttribute("board", board);
		Integer authorId = board.getAuthorId();
		model.addAttribute("authorId", authorId);
		return "community/updateBoard";
	}

	// 수정된거 넘기기
	@PostMapping("/update/{id}")
	public String updateBoard(Model model, Board board, @PathVariable("id") Integer id,
			@RequestParam(name = "authorId", required = false) Integer authorId) {
		User user = (User) httpSession.getAttribute("principal");

		board.setAuthorId(authorId);
		boardService.updateBoard(board.getId(), board.getTitle(), board.getContent(), board.getAuthorId());
		Board newBoard = boardService.readBoardDetail(id);
		model.addAttribute("board", newBoard);
		model.addAttribute("authorId", authorId);
		System.out.println("작스으으으응" + authorId);
		return "redirect:/community/board-detail/" + id; // 수정 후 상세 페이지로 이동
	}

	// 삭제하기
	@PostMapping("/delet-board/{id}")
	public String deleteBoard(Model model, Board board, @PathVariable("id") Integer id,
			@RequestParam(name = "authorId", required = false) Integer authorId,
			@RequestParam(name = "categoryId", required = false) Integer categoryId) {

		// 게시글 삭제 서비스 호출
		boardService.deleteBoard(id, authorId);

		return "redirect:/community/category/" + categoryId;
	}

}
