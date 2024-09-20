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
import com.example.demo.utils.SessionUtils;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequestMapping("/toss-pay")
@Controller
@RequiredArgsConstructor
public class TossPayController {

	@Autowired
	private TossPayService tossPayService;
	
	private final OrderService orderService;
	
	private HttpSession httpSession;


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
	    
	    Integer userId = 1;
	    // User user = (User)httpSession.getAttribute("principal");
	    // int userId = user.getUserId();
	    // tosspayservice를 통해 toss에 결제 요청 - response 값 반환
	 	String paymentKey = tossPayService.sendTossPayRequest(orderId, userId, amount);
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
    							@RequestParam("amount") Long amount) throws URISyntaxException, IOException, InterruptedException {
    	
    	//User user = (User) httpSession.getAttribute("principal");
    	// Integer userId = user.getUserId();
    	Integer userId = 1;
    	paymentKey = SessionUtils.getStringAttributeValue("paymentKey");
    	System.out.println("-----------"+orderId);
    	System.out.println(paymentKey);
    	System.out.println(amount);
    	
    	
    	// 1. tosspayservice를 통해 toss에 결제 요청 - response 값 반환
    	String responseEntity = tossPayService.sendTossPayRequestFinish(paymentKey, paymentKey,amount);
    	String getResult = responseEntity.toString();
    	
    	return responseEntity;
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