package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.dto.LecturePaymentDTO;
import com.example.demo.repository.model.Advertise;
import com.example.demo.dto.CreateLectureDTO;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.Lecture;
import com.example.demo.repository.model.Review;
import com.example.demo.repository.model.Spend;
import com.example.demo.repository.model.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.LecturePaymentService;
import com.example.demo.service.LectureService;
import com.example.demo.service.ReviewService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

	private final LectureService lectureService;
	private final ReviewService reviewService;
	private final AdminService adminService;
	private final LecturePaymentService paymentService;  
	private final HttpSession httpSession;
	
	

	@Autowired
	public LectureController(AdminService adminService, LecturePaymentService paymentService, LectureService lectureService, ReviewService reviewService, HttpSession httpSession) {
		this.lectureService = lectureService;
		this.adminService = adminService;
		this.paymentService = paymentService; 
		this.reviewService = reviewService;
		this.httpSession = httpSession;
	}

	// 카테고리별 강의 조회
	@GetMapping("/category/{categoryId}")
	public String getlectureByCategory(@PathVariable("categoryId") Integer categoryId, Model model) {
		List<Lecture> lectureList;
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		
		lectureList = lectureService.getLectureByCategory(categoryId);
		
		String categoryName = lectureService.getCategoryNameById(categoryId);
		
		model.addAttribute("categoryName" ,categoryName);
		model.addAttribute("advertiseListOne", advertiseListOne);
		model.addAttribute("advertiseListTwo", advertiseListTwo);
		model.addAttribute("advertiseListThree", advertiseListThree);
		model.addAttribute("categoryList",categoryList);

		model.addAttribute("lectureList", lectureList);
		model.addAttribute("categoryId", categoryId);

		return "lecture/list";
	}

	// 강의 상세보기
	@GetMapping("/lectureDetail/{id}")
	public String lectureDetail(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
		Lecture lecture = lectureService.readLectureDetail(id);
		List<Review> reviews = reviewService.getReviewByClassId(id);
		User user = (User)httpSession.getAttribute("principal");
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		double sum = 0;
        int i = 0;
        for(i = 0; i<reviews.size(); i++) {
            sum += reviews.get(i).getGrade();
        }
        double avg = sum / reviews.size();
		
		model.addAttribute("advertiseListOne", advertiseListOne);
		model.addAttribute("advertiseListTwo", advertiseListTwo);
		model.addAttribute("advertiseListThree", advertiseListThree);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("user", user);
		model.addAttribute("reviews", reviews);
		model.addAttribute("lecture", lecture);
		request.setAttribute("avg", avg);

		return "lecture/lectureDetail";
	}

	// 강의 리뷰 등록
	@PostMapping("/create-review")
	public String createReview(Review review, Model model) {
		User user = (User) httpSession.getAttribute("principal");
		int id = user.getUserId();
		review.setAuthor_id(id);
		reviewService.createReview(review);
		return "redirect:/lecture/lectureDetail/" + review.getClassId();
	}
	
	// 결제 완료 처리
    @PostMapping("/payment/complete")
    public String completePayment(LecturePaymentDTO paymentDTO, Model model) {
        // 세션에서 사용자 정보 가져오기
        User user = (User) httpSession.getAttribute("principal");

        // 결제 DTO에 사용자 ID 추가
        paymentDTO.setUserId(user.getUserId());
        System.out.println("!!!!!!!!!!!!!!!!!쁘에에에에에게엑엑에게" + paymentDTO);

        // 결제 처리 서비스 호출
        String result = paymentService.processPayment(paymentDTO);

        // 결제 결과에 따른 리다이렉트
        if ("success".equals(result)) {
            model.addAttribute("message", "결제가 완료되었습니다.");
            user.setCurrentCash(paymentDTO.getCurrentCash());  // 사용자 정보 업데이트
            httpSession.setAttribute("principal", user);  // 세션에 업데이트된 사용자 정보 저장
        } else {
            model.addAttribute("message", result); // 실패 메시지 전달
        }

        return "redirect:/lecture/lectureDetail/" + paymentDTO.getClassId();
    }
	

	// 내 강의 조회
	@GetMapping("/my-lecture")
	public String getMyLecture(Model model, HttpServletRequest request) {
		List<Lecture> lectureList;
		User user = (User) httpSession.getAttribute("principal");
		if (user == null) {
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			request.setAttribute("url", "/user/sign-in");
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
			request.setAttribute("url", "/user/sign-in");
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
		request.setAttribute("url", "/lecture/my-lecture");
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
			request.setAttribute("url", "/user/sign-in");
			return "alert";
		} else if (user.getUserId() != lecture.getAuthorId()) {
			request.setAttribute("msg", "수정 권한이 없습니다.");
			request.setAttribute("url", "/lecture/my-lecture");
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
				.limitNum(Integer.parseInt(request.getParameter("limitNum")))
				.id(Integer.parseInt(request.getParameter("classId")))
				.price(Long.parseLong(request.getParameter("price")))
				.totalNum(Integer.parseInt(request.getParameter("totalNum"))).build();
		int result = lectureService.updateLecture(createLecture);
		if (result == 1) {
			request.setAttribute("msg", "수정 완료");
			request.setAttribute("url", "/lecture/my-lecture");
			return "alert";
		} else {
			request.setAttribute("msg", "수정 실패, 다시 시도해 주세요");
			request.setAttribute("url", "/lecture/my-lecture");
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
			request.setAttribute("url", "/user/sign-in");
			return "alert";
		} else if (lecture.getAuthorId() != user.getUserId()) {
			request.setAttribute("msg", "권한이 없습니다.");
			request.setAttribute("url", "/lecture/my-lecture");
			return "alert";
		} else {
			lectureService.deleteLectureById(lectureId);
			request.setAttribute("msg", "강의 삭제 완료.");
			request.setAttribute("url", "/lecture/my-lecture");
			return "alert";
		}
	}

	// 수강 평 조회
	@GetMapping("/lecture-review/{id}")
	public String lectureReview(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
		User user = (User) httpSession.getAttribute("principal");
		if (user == null) {
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			request.setAttribute("url", "/user/sign-in");
			return "alert";
		}
		Lecture lecture = lectureService.readLectureDetail(id);
		if (lecture == null) {
			request.setAttribute("msg", "강의 정보를 불러오던 중 오류가 발생하였습니다. 다시 시도해 주세요");
			request.setAttribute("url", "/lecture/my-lecture");
			return "alert";
		}
		List<Review> reviews = reviewService.getReviewByClassId(id);
		double sum = 0;
		int i = 0;
		for (i = 0; i < reviews.size(); i++) {
			sum += reviews.get(i).getGrade();
		}
		double avg = sum / reviews.size();
		String formattedAvg = String.format("%.1f", avg);
		request.setAttribute("avg", formattedAvg);
		
		List<Spend> spends = lectureService.getSpendHistoryByLectureId(id);
		Long spendSum = 0L;
		for(int j = 0; j<spends.size(); j++) {
			spendSum += spends.get(j).getSpend();
		}
		
		request.setAttribute("spendSum", spendSum);
		
		model.addAttribute("spends", spends);
		model.addAttribute("user", user);
		model.addAttribute("reviews", reviews);
		model.addAttribute("lecture", lecture);

		return "lecture/lectureReview";
	}

	
}
