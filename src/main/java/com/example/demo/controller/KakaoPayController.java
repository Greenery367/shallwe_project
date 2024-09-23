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
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.OrderDetail;
import com.example.demo.repository.model.User;
import com.example.demo.service.KakaoPayService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.example.demo.utils.SessionUtils;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RequestMapping("/cash")
@Controller
@RequiredArgsConstructor
public class KakaoPayController {
	
	private final HttpSession httpSession;
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
		User user = (User)httpSession.getAttribute("principal");
		if(user == null) {
			return "/sign/signIn";
		}
		
		model.addAttribute("user",user);
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
		User user = (User)httpSession.getAttribute("principal");
		
		// 1. 가주문 생성하기 (보안을 위한 정보 저장)
		Long totalPrice = Long.valueOf(totalAmount); // 결제 가격
		String orderId = orderService.makeNewOrder(user.getUserId(),totalPrice,1, null); // 가주문 생성

		// 2. 결제 요청 메세지 보내기
		ReadyResponseDTO readyResponseDTO = kakaoPayService.payReady(totalPrice,orderId); //  결제 요청

		// 3. tid 생성하기
		SessionUtils.addAtribute("tid", readyResponseDTO.getTid()); // tid 생성
		orderService.setTid(orderId, readyResponseDTO.getTid());

		// 4. response 값 보내기 - chargeCash.jsp의 redirect_url_pc로 연결
		return readyResponseDTO;
	}
	
	/**
	 * 카카오 페이 - 결제 승인 페이지
	 * @param model
	 * @return
	 */
	// http://localhost:8080/cash/result/kakao
	@GetMapping("/result/kakao")
	public String resultKakaoPay(@RequestParam("pg_token") String pgToken) {
		
		// 유저 정보 가지고 오기
		User user = (User)httpSession.getAttribute("principal");
		
		// 1. 결제 보안 처리를 위한 정보 가져오기
		// (1) tid 가져오기
	    String tid = SessionUtils.getStringAttributeValue("tid");
	    // (2) 주문 세부사항 가져오기
	    OrderDetail orderDetail = orderService.checkOrder(tid);
	    // (3) 예외 처리 - 만약 가주문 정보가 없다면, 정상적인 주문 경로 x -> return
	    if(orderDetail == null) {
	    	// 예외 처리
	    	return null;
	    }
	    
	 
	    
	    // 2. 주문 세부사항을 통해 가주문 - 진주문 대조 확인 (인증)
	    Order checkOrderRecord = orderService.checkOrderRecord(user.getUserId(),orderDetail);
	    
	    // 3. 만약 주문 테이블이 있다면 ...
	    if(checkOrderRecord != null) {
	    	// (1) 인증된 유저는 카카오 결제 승인 요청
		    ApproveResponseDTO approveResponseDTO = kakaoPayService.payApprove(tid, pgToken);
		    
		    // (2) 결제 후 -> DB 상의 유저 캐쉬 변경
		    userService.updateUserCash(user.getUserId(),checkOrderRecord.amount);
		    
		    // (3) 주문 상태 변경
		    orderService.changeOrderStatus(checkOrderRecord.orderId);
		    
	    }
	    
	    // 3. 보안 인증을 거치지 못한다면 실패 페이지로 이동
	    else {
	    	return "/cash/chargeFailed";
	    }
	    
	    // 4. 유저 캐쉬 상태 변경
	    orderService.updateUsersCurrentCash(user.getUserId(),checkOrderRecord.getAmount());
	    
	    return "/cash/chargeResult";
	}
	
	
	/**
	 * 카카오 페이 - 결제 취소 페이지
	 * @param model
	 * @return
	 */
	// http://localhost:8080/cash/canceled/kakao
	@GetMapping("/canceled/kakao")
	public String canceledKakaoPay(@RequestParam("pg_token") String pgToken) {
		// 가주문 삭제
	    return "/cash/chargeCanceledToss";
	}
	
	/**
	 * 카카오 페이 - 결제 실패 페이지
	 * @param model
	 * @return
	 */
	// http://localhost:8080/cash/failed/kakao
	@GetMapping("/failed/kakao")
	public String failedKakaoPay(@RequestParam("pg_token") String pgToken) {
		// 가주문 삭제
	    return "/cash/chargeFailedToss";
	}
	

	

}
