package com.example.demo.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ApproveResponseDTO;
import com.example.demo.dto.ReadyResponseDTO;
import com.example.demo.dto.TossPayDTO;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.OrderDetail;
import com.example.demo.service.KakaoPayService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.example.demo.utils.SessionUtils;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RequestMapping("/cash")
@Controller
@RequiredArgsConstructor
public class CashController {
	
	private HttpSession httpSession;
	private final KakaoPayService kakaoPayService;
	private final OrderService orderService;
	private final UserService userService;
	
	/**
	 *  캐쉬 충전 페이지
	 * @return : 캐쉬 충전 페이지로 이동
	 */
	// http://localhost:8080/cash/charge
	@GetMapping("/charge")
	public String chargeCash(Model model) {
		return "/cash/chargeCash";
	}
	
	/**
	 * 카카오 페이 - 결제 요청 페이지
	 * @param txId
	 * @param paymentId
	 * @return
	 */
	// http://localhost:8080/cash/send-request-kakao
	@PostMapping("/send-request/kakao/{totalAmount}")
	@ResponseBody
	public ReadyResponseDTO payCashByKakao(@PathVariable("totalAmount") String totalAmount) {
		
		int totalPrice = Integer.valueOf(totalAmount); // 결제 가격
		String orderId = orderService.makeNewOrder(1,totalPrice,1); // 가주문 생성
		ReadyResponseDTO readyResponseDTO = kakaoPayService.payReady(totalPrice,orderId); //  결제 요청
		SessionUtils.addAtribute("tid", readyResponseDTO.getTid()); // tid
		orderService.setTid(orderId, readyResponseDTO.getTid());
		
		return readyResponseDTO;
	}
	
	/**
	 * 카카오 페이 - 결제 승인 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/result/kakao")
	public String resultKakaoPay(@RequestParam("pg_token") String pgToken) {
		
		// tid 가져오기
	    String tid = SessionUtils.getStringAttributeValue("tid");
	    // 주문 세부사항 가져오기
	    OrderDetail orderDetail = orderService.checkOrder(tid);
	    // 예외 처리
	    if(orderDetail == null) {
	    	// 예외 처리
	    	return null;
	    }
	    int userId = 1;
	    
	    // 주문 세부사항을 통해 가주문 - 진주문 확인 (인증)
	    Order checkOrderRecord = orderService.checkOrderRecord(userId,orderDetail);
	    
	    if(checkOrderRecord != null) {
	    	// 인증된 유저는 카카오 결제 요청
		    ApproveResponseDTO approveResponseDTO = kakaoPayService.payApprove(tid, pgToken);
		    
		    // 결제 후 -> 유저 캐쉬 변경
		    userService.updateUserCash(userId,checkOrderRecord.amount);
		    
		    // 주문 상태 변경
		    orderService.changeOrderStatus(checkOrderRecord.orderId);
		    
		    // TODO - 캐쉬 히스토리 생성
	    }
	    
	    else {
	    	return "/cash/chargeFailedKakao";
	    }
	    
	    
	    
	    // 유저 캐쉬+
	    return "/cash/chargeResultKakao";
	}
	
	
	/**
	 * 카카오 페이 - 결제 취소 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/canceled/kakao")
	public String canceledKakaoPay(@RequestParam("pg_token") String pgToken) {
		
	    return "/cash/chargeCanceledToss";
	}
	
	/**
	 * 카카오 페이 - 결제 실패 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/failed/kakao")
	public String failedKakaoPay(@RequestParam("pg_token") String pgToken) {
		
	    return "/cash/chargeFailedToss";
	}
	

	

}
