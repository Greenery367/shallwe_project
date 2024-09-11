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
	 * 환불 페이지 접속
	 * @param model
	 * @return
	 */
	// http://localhost:8080/admin/refund/
	@GetMapping("/")
	public String refundBoardPage(Model model) {
		List<Refund> refundList = refundService.getAllRefund(4, 0);
		model.addAttribute("refundList", refundList);
		return "/admin/adminRefund";
	}
	
	/**
	 * 환불 처리
	 * @param refundData
	 * @return
	 */
	@PostMapping("/send-request")
	@ResponseBody
	public ResponseEntity<RefundResponseDTO> postMethodName(@RequestBody Refund refundData) {
		RefundResponseDTO refundResponseDTO = refundService.readyRefund(refundData);
		
		System.out.println(refundResponseDTO);
		
		return new ResponseEntity<>(refundResponseDTO,HttpStatus.OK);
	}
	
	
	
}
