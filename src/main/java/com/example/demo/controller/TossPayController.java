package com.example.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.TossPayApproveResponseDTO;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.OrderDetail;
import com.example.demo.repository.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.TossPayService;
import com.example.demo.service.UserService;
import com.example.demo.utils.SessionUtils;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequestMapping("/toss-pay")
@Controller
@RequiredArgsConstructor
public class TossPayController {

	@Autowired
	private TossPayService tossPayService;
	@Autowired
	private final OrderService orderService;
	@Autowired
	private HttpSession httpSession;
	private final UserService userService;

	/**
	 * 토스 페이 - 결제 전 정보 저장 (정보 무결성)
	 * @param orderId
	 * @param paymentKey
	 * @param amount
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws URISyntaxException
	 */
	@ResponseBody
	@PostMapping("/send-request")
	public void resultTossPay(@RequestBody Map<String, Object> payload) throws UnsupportedEncodingException, URISyntaxException {
	    
		// 받은 정보들 파싱
		String orderId = (String) payload.get("orderId");
	    Long amount = Long.valueOf(payload.get("totalAmount").toString());
	    
	    System.out.println(orderId);
	    
	    User user = (User)httpSession.getAttribute("principal");
	    int userId = user.getUserId();
	    // tosspayservice를 통해 toss에 결제 요청 - response 값 반환
	 	String paymentKey = tossPayService.sendTossPayRequest(orderId, orderId, userId, amount);
	 	SessionUtils.addAtribute("paymentKey", paymentKey); // paymentKey 생성
	 	
	 	return;
 }
    
	
    /**
     * 토스 페이먼츠 - 결제 요청
     * @return 
     * @throws URISyntaxException 
     * @throws InterruptedException 
     * @throws IOException 
     */
 	@GetMapping("/success")
    public String resultTossPay(@RequestParam("orderId")String orderId,
    							@RequestParam("paymentKey")String paymentKey,
    							@RequestParam("amount") Long amount,
    							Model model) throws URISyntaxException, IOException, InterruptedException {
    	
 		User user = (User)httpSession.getAttribute("principal");
    	Integer userId = user.getUserId();
    	
    	// 가주문 생성
	 	tossPayService.sendTossPayRequest(paymentKey, orderId, userId, amount);
	 	SessionUtils.addAtribute("paymentKey", paymentKey); // paymentKey 생성
    	
    	// tosspayservice를 통해 toss에 결제 요청 - response 값 반환
    	String responseEntity = tossPayService.sendTossPayRequestFinish(orderId, paymentKey,amount);
    	String getResult = responseEntity.toString();
    	
    	// 주문 상태 변경
	    orderService.changeOrderStatus(paymentKey);
    	
    	// 유저 캐쉬 상태 변경
	    orderService.updateUsersCurrentCash(user.getUserId(),amount);
	    User updateUser = userService.searchByUserId(user.getUserId());
	    
	    httpSession.setAttribute("principal",user);
	    model.addAttribute("user", updateUser);
    	
    	if(responseEntity.contains("code")) {
    		return "/cash/chargeResult" ;
    	}
    	
    	return "/cash/chargeResult" ;
    }
    
    /**
     * 결제 성공 페이지
     * @param pgToken
     * @return
     */
    // http://localhost:8080/toss-pay/result
 	@GetMapping("/result11")
 	public String resultKakaoPay(@RequestParam("pg_token") String pgToken) {
 		
 		return "../test/main";
 	}
 	
 	
}