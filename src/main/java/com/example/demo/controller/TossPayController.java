package com.example.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.TossPayApproveResponseDTO;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.OrderDetail;
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
	 * 토스 페이 - 결제 승인 페이지
	 * @param orderId
	 * @param paymentKey
	 * @param amount
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws URISyntaxException
	 */
    @GetMapping("/send-request")
    public String resultTossPay(@RequestParam("orderId") String orderId,
                                @RequestParam("paymentKey") String paymentKey,
                                @RequestParam("amount") Long amount,
                                Model model) throws UnsupportedEncodingException, URISyntaxException {
    	Integer userId = 1;
    	// tosspayservice를 통해 toss에 결제 요청 - response 값 반환
    	TossPayApproveResponseDTO responseEntity = tossPayService.sendTossPayRequest(orderId, paymentKey,amount);

    	String getResult = responseEntity.toString();

        // 결제 결과 - 요청 확인
        if(responseEntity != null){
  
    	    // 주문 세부사항 가져오기
        	// tid 가져오기
    	    String tid = SessionUtils.getStringAttributeValue("tid");
  
    	    // 주문 세부사항 가져오기
    	    OrderDetail orderDetail = orderService.checkOrderByOrderId(tid);

    	    // DB 상의 정보가 있다면...
        	if(orderDetail != null) {
        		// order status 변경
        		// 1. order 객체 반환
        		Order getOrder = orderService.checkOrderRecord(userId, orderDetail);
        		
        		// 2. order 객체 - 상태값 변경
        		orderService.changeOrderStatus(getOrder.orderId);
       			// 대조 후 결과창 반환
               	return "/cash/chargeResult";
       		}
       		else {
       			// order - orderDetail 삭제
       			orderService.deleteFailedOrder(orderDetail.orderId, tid, userId);
       			// 대조 후 결과창 반환
               	return "/cash/chargeFailed";
       		}
        	
        // 결제 결과 - 결제 실패
        } else {
        	return "/cash/chargeFailed";
        }
    }
    
    // http://localhost:8080/toss-pay/result
 	@GetMapping("/result")
 	public String resultKakaoPay(@RequestParam("pg_token") String pgToken) {
 		
 		return "../test/main";
 	}
}