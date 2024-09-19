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
import com.example.demo.repository.model.Board;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.Lecture;
import com.example.demo.repository.model.Review;
import com.example.demo.repository.model.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.LectureService;
import com.example.demo.service.LecturePaymentService;
import com.example.demo.service.ReviewService;

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
	
	
	// 카테고리별 강의 조회
	@GetMapping("/category/{categoryId}")
	public String getlectureByCategory(@PathVariable("categoryId") Integer categoryId, Model model) {
		List<Lecture> lectureList;
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		
		lectureList = lectureService.getLectureByCategory(categoryId);
		
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
	public String lectureDetail(@PathVariable("id") Integer id, Model model) {
		Lecture lecture = lectureService.readLectureDetail(id);
		List<Review> reviews = reviewService.getReviewByClassId(id);
		User user = (User)httpSession.getAttribute("principal");
		
		model.addAttribute("user", user);
		model.addAttribute("reviews", reviews);
		model.addAttribute("lecture", lecture);
		
		
		return "lecture/lectureDetail";
	}
	
	// 강의 리뷰 등록
	@PostMapping("/create-review")
	public String createReview(Review review, Model model) {
		User user = (User)httpSession.getAttribute("principal");
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
        } else {
            model.addAttribute("message", result); // 실패 메시지 전달
        }

        return "redirect:/lecture/lectureDetail/" + paymentDTO.getClassId();
    }
	
	
}
