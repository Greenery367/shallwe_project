package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.FAQDTO;
import com.example.demo.dto.FrequeDTO;
import com.example.demo.handler.exception.DataDeleveryException;
import com.example.demo.repository.model.User;
import com.example.demo.service.CsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@Controller
@RequestMapping("/cs")
public class CsController {
	
	@Autowired
	private final CsService csService;
	@Autowired
	private final HttpSession session;

	/*
	 * 고객센터 홈 화면 -> 고객지원 탭 클릭 -> 자주 묻는 질문 목록 표출
	 */
	@GetMapping("/main")
	public String csMain(Model model, HttpServletRequest httpServletRequest,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "siez", defaultValue = "10") int size) {

		int totalRecords = csService.countFreq();
		int totalPages = (int) Math.ceil((double) totalRecords / size);
		List<FrequeDTO> postList = csService.readAllFreq(page, size);
		model.addAttribute("postList", postList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("curruntPage", page);
		model.addAttribute("size", size);
		httpServletRequest.setAttribute("totalPages", totalPages);
		httpServletRequest.setAttribute("curruntPage", page);
		return "cs/csMain";
	}

	
	
	/*
	 * 고객지원 메인 페이지에서 navbar의 FAQ 클릭시 FAQ목록 불러와서 페이징 처리
	 */
	@GetMapping("/FAQ")
	public String getFAQ(Model model, HttpServletRequest httpServletRequest,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "siez", defaultValue = "10") int size) {
		int totalRecords = csService.countFAQ();
		int totalPages = (int) Math.ceil((double) totalRecords / size);
		List<FAQDTO> postList = csService.readAllFAQ(page, size);
		System.out.println(postList.toString());
		model.addAttribute("postList", postList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("curruntPage", page);
		model.addAttribute("size", size);
		httpServletRequest.setAttribute("totalPages", totalPages);
		httpServletRequest.setAttribute("curruntPage", page);
		return "cs/csFAQ";
	}

	
	/*
	 * 문의글 작성 페이지 이동 처리
	 */
	@GetMapping("post-FAQ")
	public String postForm() {
		return "cs/postFAQ";
	}

	/*
	 * 문의글 제출
	 */
	@PostMapping("post-FAQ")
	public String postQNA(HttpServletRequest request) {
		User user = (User) session.getAttribute("principal");
		FAQDTO dto = FAQDTO.builder().title(request.getParameter("title")).userId(user.getId())
				.writer(user.getNickname()).content(request.getParameter("content")).status(0).build();
		csService.createFAQ(dto);
		request.setAttribute("msg", "문의글 작성 완료");
		request.setAttribute("url", "FAQ");
		return "alert";
	}

	/*
	 * 문의글 목록에서 문의글 선택시 -> 상세보기 창 이동
	 */
	@GetMapping("detail")
	public String getFAQDetail(@RequestParam("writer") String writer, @RequestParam("id") String id,
			@RequestParam("status") String status, Model model, HttpServletRequest request) {

		if (id == null || id.trim().isEmpty()) {
			// id 값이 null이거나 빈 문자열인 경우 처리
			request.setAttribute("msg", "유효하지 않은 ID 값입니다.");
			request.setAttribute("url", "FAQ");
			return "alert";
		}
		try {
			User user = (User) request.getSession().getAttribute("principal");
			try {
				Integer Pid = Integer.parseInt(id.trim()); // id 파라미터를 정수로 변환

				if (user.getNickname().equals(writer) || Integer.parseInt(status) == 1) {   // 작성자가 일치하거나 status가 1일때 (답글일 때) 조회 
					FAQDTO dto = csService.readFAQById(Pid);
					model.addAttribute("FAQ", dto);
					request.setAttribute("status", status);
					return "cs/detailFAQ";
				} else {
					request.setAttribute("msg", "열람 권한이 없습니다.");
					request.setAttribute("url", "FAQ");
					return "alert";
				}
			} catch (NumberFormatException e) {
				request.setAttribute("msg", "유효하지 않은 ID 값입니다.@@@@@@@@");
				request.setAttribute("url", "FAQ");
				return "alert";
			}
		} catch (Exception e) {
			throw new DataDeleveryException("로그인 후 이용가능합니다.", HttpStatus.BAD_REQUEST);
		}
	}

	// TODO 관리자 테이블 생성후 컬럼 확정시 CRUD 구현
	/*
	 * 자주 묻는 질문 목록에서 클릭시 -> 상세보기 이동
	 */
	@GetMapping("detailFreq")
	public String getFreqDetail(@RequestParam("writer") String writer, @RequestParam("id") String id, Model model,
			HttpServletRequest request) {
		if (id == null || id.trim().isEmpty()) {
			// id 값이 null이거나 빈 문자열인 경우 처리
			request.setAttribute("msg", "유효하지 않은 ID 값입니다.");
			request.setAttribute("url", "FAQ");
			return "alert";
		}
			try {
				Integer Pid = Integer.parseInt(id.trim()); // id 파라미터를 정수로 변환
					FrequeDTO dto = csService.readFreqById(Pid);
					model.addAttribute("FAQ", dto);
					return "cs/detailFreq";
			} catch (NumberFormatException e) {
				request.setAttribute("msg", "유효하지 않은 ID 값입니다.@@@@@@@@");
				request.setAttribute("url", "FAQ");
				return "alert";
			}
		}
	@GetMapping("update")
	public String getUpdateRequest(@RequestParam("id") String idstr, Model model, HttpServletRequest request) {
		try {
			if (idstr == null) {
				throw new DataDeleveryException("유효하지 않은 id 입니다.", HttpStatus.BAD_REQUEST);
			}
			Integer id = Integer.parseInt(idstr.trim());
			FAQDTO dto = csService.readFAQById(id);
			model.addAttribute("FAQ", dto);
			return "cs/updateFAQ";
		} catch (NumberFormatException e) {
			throw new DataDeleveryException("유효하지 않은 id 입니다..", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("update/{id}")
	public String postMethodName(@PathVariable(name = "id") Integer id, Model model, HttpServletRequest request) {
		int result = csService.updateFAQ(id, request.getParameter("title"), request.getParameter("content"));
		if(result == 1) {
			request.setAttribute("msg", "문의글 수정 완료");
			request.setAttribute("url", "/cs/FAQ");
			return "alert";
		}else {
			throw new DataDeleveryException("문의글 수정에 실패하셨습니다. 잠시 후 다시 시도해 주세요", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("delete")
	public String deleteFAQ(@RequestParam("id") String idstr, Model model, HttpServletRequest request) {
		Integer id = Integer.parseInt(idstr.trim());
		csService.deleteFAQ(id);
		request.setAttribute("msg", "삭제 완료");
		request.setAttribute("url", "/cs/FAQ");
		return "alert";
	}
	
}
