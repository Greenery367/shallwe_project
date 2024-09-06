package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.FAQDTO;
import com.example.demo.dto.FrequeDTO;
import com.example.demo.repository.model.User;
import com.example.demo.service.CsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Controller
@RequestMapping("/cs")
public class CsController {
	
	
	
	@Autowired
	private final CsService csService;
	@Autowired
	private final HttpSession session;
	
	/*
	 * 고객센터 홈 화면 -> 고객지원 탭 클릭 
	 * -> 자주 묻는 질문 목록 표출
	 */
	@GetMapping("/main")
	public String csMain(Model model, HttpServletRequest httpServletRequest, 
			@RequestParam(name ="page", defaultValue = "1") int page,
			@RequestParam(name ="siez", defaultValue = "10") int size) {
		
		int totalRecords = csService.countFreq();
    	int totalPages = (int)Math.ceil((double) totalRecords / size);
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
			@RequestParam(name ="page", defaultValue = "1") int page,
			@RequestParam(name ="siez", defaultValue = "10") int size) {
		int totalRecords = csService.countFAQ();
    	int totalPages = (int)Math.ceil((double) totalRecords / size);
    	List<FAQDTO> postList = csService.readAllFAQ(page, size);
    	model.addAttribute("postList", postList);
    	model.addAttribute("totalPages", totalPages);
    	model.addAttribute("curruntPage", page);
    	model.addAttribute("size", size);
    	httpServletRequest.setAttribute("totalPages", totalPages);
    	httpServletRequest.setAttribute("curruntPage", page);
    	System.out.println("12312312132" + postList.toString());
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
	@Transactional
	@PostMapping("post-FAQ")
	public String postQNA(HttpServletRequest request) {
		User user = (User) session.getAttribute("principal");
		FAQDTO dto = FAQDTO.builder().title(request.getParameter("title"))
									.userId(user.getId())
									.writer(user.getNickname())
									.content(request.getParameter("content"))
									.build();
		csService.createFAQ(dto);
		request.setAttribute("msg", "문의글 작성 완료");
        request.setAttribute("url", "FAQ");
        return "alert";
	}
	
	/*
	 * 문의글 목록에서 문의글 선택시 -> 상세보기 창 이동
	 */
	@GetMapping("detail")
	public String getFAQDetail(@RequestParam("writer") String writer, 
            @RequestParam("id") int id,
            Model model, HttpServletRequest request) {
		User user = (User) session.getAttribute("principal");
		if(user.getNickname().equals(writer)) {
			FAQDTO dto = csService.readFAQById(id);
			model.addAttribute("FAQ",dto);
			return "cs/detailFAQ";
		}else {
			request.setAttribute("msg", "열람 권한이 없습니다.");
	        request.setAttribute("url", "FAQ");
	        return "alert";
		}
	}
	
	// TODO 관리자 테이블 생성후 컬럼 확정시 CRUD 구현
	/*
	 * 자주 묻는 질문 목록에서 클릭시 -> 상세보기 이동
	 */
	@GetMapping("detailFreq")
	public String getFreqDetail(@RequestParam("writer") String writer, 
            @RequestParam("id") int id,
            Model model, HttpServletRequest request) {
			FrequeDTO dto = csService.readFreqById(id);
			model.addAttribute("FAQ",dto);
			return "cs/detailFreq";
	}
	
	
	@GetMapping("update")
	public String getUpdateRequest(@RequestParam("id") String id,
									Model model, HttpServletRequest request) {
		
		
		
		
		return new String();
	}
	
	
	
	
}
