package com.example.demo.controller;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.CreateLectureDTO;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.Lecture;
import com.example.demo.repository.model.Review;
import com.example.demo.repository.model.User;
import com.example.demo.service.LectureService;
import com.example.demo.service.ReviewService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/lecture")
public class LectureController {

	private final LectureService lectureService;
	private final ReviewService reviewService;
	private final HttpSession httpSession;

	@Autowired
	public LectureController(LectureService lectureService, ReviewService reviewService, HttpSession httpSession) {
		this.lectureService = lectureService;
		this.reviewService = reviewService;
		this.httpSession = httpSession;
	}

	// 카테고리별 강의 조회
	@GetMapping("/category/{categoryId}")
	public String getlectureByCategory(@PathVariable("categoryId") Integer categoryId, Model model) {
		List<Lecture> lectureList;

		lectureList = lectureService.getLectureByCategory(categoryId);
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("categoryId", categoryId);

		return "lecture/list";
	}

	// 강의 상세보기
	@GetMapping("/lectureDetail/{id}")
	public String lectureDetail(@PathVariable("id") Integer id, Model model) {
		Lecture lecture = lectureService.readLectureDetail(id);
		List<Review> reviews = reviewService.getReviewByClassId(id);
		User user = (User) httpSession.getAttribute("principal");

		model.addAttribute("user", user);
		model.addAttribute("reviews", reviews);
		model.addAttribute("lecture", lecture);

		return "lecture/lectureDetail";
	}

	// 강의 리뷰 등록
	@PostMapping("/createReview")
	public String createReview(Review review, Model model) {
		User user = (User) httpSession.getAttribute("principal");
		int id = user.getUserId();
		review.setAuthor_id(id);
		reviewService.createReview(review);
		return "redirect:/lecture/lectureDetail/" + review.getClassId();
	}

	// 내 강의 조회
	@GetMapping("/my-lecture")
	public String getMyLecture(Model model, HttpServletRequest request) {
		List<Lecture> lectureList;
		User user = (User) httpSession.getAttribute("principal");
		if (user == null) {
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			request.setAttribute("url", "sign-in");
			return "alert";
		}
		lectureList = lectureService.getLectureListByUserId(user.getUserId());
		model.addAttribute("lectureList", lectureList);

		return "lecture/myLectureList";
	}

	// 강의 개설 버튼 클릭 --> 강의 생성 페이지 이동 처리
	@GetMapping("/create-lecture")
	public String requestCreateLectureForm(Model model, HttpServletRequest request) {
		User user = (User) httpSession.getAttribute("principal");
		if (user == null) {
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			request.setAttribute("url", "sign-in");
			return "alert";
		}
		model.addAttribute("user", user);
		List<Category> categoryList = lectureService.getCategory();
		model.addAttribute("categoryList", categoryList);
		return "lecture/createLecture";
	}

	// 강의 생성 페이지 -> 강의 생성 요청 -> DB저장 후 내강의리스트 리턴
	@PostMapping("/create-lecture")
	public String createLecture(HttpServletRequest request) {
		CreateLectureDTO createLecture = CreateLectureDTO.builder().categoryId(0)
				.categoryId(Integer.parseInt(request.getParameter("categoryId")))
				.authorId(Integer.parseInt(request.getParameter("authorId"))).title(request.getParameter("title"))
				.subtitle(request.getParameter("subtitle")).content(request.getParameter("content"))
				.limitNum(Integer.parseInt(request.getParameter("limitNum")))
				.price(Long.parseLong(request.getParameter("price")))
				.totalNum(Integer.parseInt(request.getParameter("totalNum"))).build();
		lectureService.createLecture(createLecture);
		request.setAttribute("msg", "강좌 개설 완료");
		request.setAttribute("url", "my-lecture");
		return "alert";
	}

	// 강의 정보 수정 페이지 요청
	@GetMapping("/lecture-update/{lectureId}")
	public String requestUpdateLectureForm(@PathVariable("lectureId") Integer lectureId, Model model,
			HttpServletRequest request) {
		Lecture lecture = lectureService.readLectureDetail(lectureId);
		User user = (User) httpSession.getAttribute("principal");
		if (user == null) {
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			request.setAttribute("url", "sign-in");
			return "alert";
		} else if (user.getUserId() != lecture.getAuthorId()) {
			request.setAttribute("msg", "수정 권한이 없습니다.");
			request.setAttribute("url", "my-lecture");
			return "alert";
		} else {
			model.addAttribute("user", user);
			model.addAttribute("lecture", lecture);
			List<Category> categoryList = lectureService.getCategory();
			model.addAttribute("categoryList", categoryList);
			return "lecture/updateLecture";
		}
	}

	// 강의 내용 수정 페이지 -> 강의 정보 수정 요청 -> DB저장후 내 강의 리스트 리턴
	@PostMapping("/lecture-update")
	public String updateLecture(HttpServletRequest request) {
		CreateLectureDTO createLecture = CreateLectureDTO.builder().categoryId(0)
				.categoryId(Integer.parseInt(request.getParameter("categoryId"))).title(request.getParameter("title"))
				.subtitle(request.getParameter("subtitle")).content(request.getParameter("content"))
				.limitNum(Integer.parseInt(request.getParameter("limitNum"))).id(Integer.parseInt(request.getParameter("classId")))
				.price(Long.parseLong(request.getParameter("price")))
				.totalNum(Integer.parseInt(request.getParameter("totalNum"))).build();
		int result = lectureService.updateLecture(createLecture);
		if (result == 1) {
			request.setAttribute("msg", "수정 완료");
			request.setAttribute("url", "my-lecture");
			return "alert";
		} else {
			request.setAttribute("msg", "수정 실패, 다시 시도해 주세요");
			request.setAttribute("url", "my-lecture");
			return "alert";
		}
	}
	
	// 삭제 요청 처리
	@PostMapping("/lecture-delete/{lectureId}")
	public String deleteLecture(@PathVariable("lectureId") Integer lectureId, HttpServletRequest request) {
		Lecture lecture = lectureService.readLectureDetail(lectureId);
		User user = (User) httpSession.getAttribute("principal");
		if (user == null) {
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			request.setAttribute("url", "sign-in");
			return "alert";
		}else if(lecture.getAuthorId() != user.getUserId()){
			request.setAttribute("msg", "권한이 없습니다.");
			request.setAttribute("url", "my-lecture");
			return "alert";
		}else {
			lectureService.deleteLectureById(lectureId);
			request.setAttribute("msg", "강의 삭제 완료.");
			request.setAttribute("url", "/lecture/my-lecture");
			return "alert";
		}
	}
	
	// 강의 아이디로 강의 수강평 조회  
	
}
