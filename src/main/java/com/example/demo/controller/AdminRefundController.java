package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.ChangeRefundDto;
import com.example.demo.dto.IdDTO;
import com.example.demo.dto.RefundDTO;
import com.example.demo.dto.RefundResponseDTO;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.Refund;
import com.example.demo.service.OrderService;
import com.example.demo.service.RefundService;


@Controller
@RequestMapping("/admin/refund")
@RequiredArgsConstructor
public class AdminRefundController {

	@Autowired
	private HttpSession httpSession;
	@Autowired
	private final OrderService orderService;
	private final RefundService refundService;
	
	
	/**
	 * 관리자 환불 페이지 접속
	 * @param model
	 * @return
	 */
	// http://localhost:8080/admin/refund
	@GetMapping("")
	public String refundBoardPage(Model model) {
		List<RefundDTO> refundList = refundService.getAllRefundDto(10, 0);
		model.addAttribute("refundList", refundList);
		return "/admin/adminRefund";
	}
	
	/**
	 * 카카오 - 환불 처리
	 * @param refundData
	 * @return
	 */
	@PostMapping("/send-request/kakao")
	@ResponseBody
	public ResponseEntity<RefundResponseDTO> sendRefundToKakao(@RequestBody IdDTO id) {
		
		Refund refund = refundService.getRefundById(Integer.parseInt(id.getId()));
		
		// 환불 요청
		RefundResponseDTO refundResponseDTO = refundService.readyRefundForKakao(refund);
		
		// 환불 결과 받아오기
		return new ResponseEntity<>(refundResponseDTO,HttpStatus.OK);
	}
	
	/**
	 * 토스 - 환불 처리
	 * @param refundData
	 * @return
	 */
	@PostMapping("/send-request/toss")
	@ResponseBody
	public List sendRefundToToss(@RequestBody IdDTO id) {
		
		System.out.println("흠흠흠흠흠 토스"+id);
		
		// 환불 요청
		List response = refundService.readyRefundToToss(id.getId());
		
		// 환불 결과 받아오기
		return response;
	}
	
	
	
}
