package com.example.demo.controller;

import java.io.IOException;
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
	public String sendRefundToKakao(@RequestBody IdDTO id, Model model) {
		
		Refund refund = refundService.getRefundById(Integer.parseInt(id.getId()));
		
		refundService.updateRefundStatus(Integer.parseInt(id.id));
		
		// 환불 요청
		RefundResponseDTO refundResponseDTO = refundService.readyRefundForKakao(refund);
		
		// 환불 결과 받아오기
		new ResponseEntity<>(refundResponseDTO,HttpStatus.OK);
		
		List<RefundDTO> refundList = refundService.getAllRefundDto(10, 0);
		model.addAttribute("refundList", refundList);

		System.out.println("!!!!!!!!!!!!!!!!!"+refundList);
		return "/admin/adminRefund";
	}
	
	/**
	 * 토스 - 환불 처리
	 * @param refundData
	 * @return
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@PostMapping("/send-request/toss")
	@ResponseBody
	public String sendRefundToToss(@RequestBody IdDTO id, Model model) throws IOException, InterruptedException {
		
		// DB 상의 데이터 처리
		refundService.updateRefundStatus(Integer.parseInt(id.id));
		
		// 환불 요청
		refundService.readyRefundToToss(id.getId());
		
		List<RefundDTO> refundList = refundService.getAllRefundDto(10, 0);
		model.addAttribute("refundList", refundList);
		
		// 환불 결과 받아오기
		return  "/admin/adminRefund";
	}
	
	
	
}
