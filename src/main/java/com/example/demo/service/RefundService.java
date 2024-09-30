package com.example.demo.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.CashRefundDTO;
import com.example.demo.dto.RefundDTO;
import com.example.demo.dto.RefundResponseDTO;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RefundRepository;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.OrderDetail;
import com.example.demo.repository.model.Refund;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefundService {
	
	@Autowired
	public RefundRepository refundRepository;
	@Autowired
	public OrderRepository orderRepository;
	@Autowired
	public OrderDetailRepository orderDetailRepository;
	
	
	public void requestRefund(CashRefundDTO cashRefundDTO) {
        // 환불 신청을 DB에 저장
        refundRepository.insertRefundRequest(cashRefundDTO);
    }
	
	@Transactional
    public boolean hasRequestedCashRefund(int orderId, int userId) {
        return refundRepository.checkRefundRequest(orderId, userId) > 0;
    }
	
	/**
	 * 모든 환불 정보 객체 가져오기
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<RefundDTO> getAllRefundDto (Integer limit, Integer offset){
		List<RefundDTO> allRefundDtoList = refundRepository.selectAllRefundDto(limit,offset);
		return allRefundDtoList;
	}
	
	public void updateRefundStatus(int id) {
		refundRepository.updateRefundStatus(id);
		return;
	}
	
	/**
	 * id로 환불 내역 조회하기
	 * @param refundId
	 * @return
	 */
	public Refund getRefundById(int refundId) {
		return refundRepository.selectRefundById(refundId);
	}
	
	/**
	 * 카카오페이 - 환불 요청
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Refund> getAllRefund(Integer limit, Integer offset) {
		List<Refund> refundList = refundRepository.selectAllRefund(limit, offset);
		return refundList;
	}

	/**
	 * 카카오 페이 - 환불 처리
	 * @param refund
	 * @return
	 */
	public com.example.demo.dto.RefundResponseDTO readyRefundForKakao(Refund refundDto) {
		
		// order amount 찾기
		Order order = orderRepository.selectOrderById(refundDto.orderId);
		OrderDetail orderDetail = orderDetailRepository.selectOrderDetailByOrderId(order.orderId);
		Long cancelAmount = order.getAmount();
		
		Map<String,String> parameters = new HashMap<>();
		parameters.put("cid", "TC0ONETIME");
		parameters.put("tid", orderDetail.tid);
		parameters.put("cancel_amount", (cancelAmount.toString()));
		parameters.put("cancel_tax_free_amount", "0");
		parameters.put("cancel_vat_amount", "0");
		parameters.put("cancel_available_amount",order.getAmount().toString());
		
		// HttpMessage 만들기
		HttpEntity<Map<String,String>> requestEntity = new HttpEntity(parameters,this.getKakaoHeaders());
		System.out.println(requestEntity);
		RestTemplate template = new RestTemplate();
		// 환불 요청 url
        String url = "https://open-api.kakaopay.com/online/v1/payment/cancel";
        
        // 응답 객체 생성
        RefundResponseDTO refundResponse = template.postForObject(url, requestEntity, RefundResponseDTO.class);
        System.out.println(refundResponse);
        return refundResponse;
	}
	
	/**
	 * 카카오 헤더 생성
	 * @return
	 */
	private org.springframework.http.HttpHeaders getKakaoHeaders(){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Authorization", "시크시크릿키");
		headers.set("Content-type", "application/json");
        
        return headers;
	}
	
	public List<Refund> refundFindById(Integer userId) {
		List<Refund> refundList = new ArrayList<>();
		refundList = refundRepository.refundFindById(userId);
		return refundList;
		}
	
	/**
	 * 토스 페이 - 환불 처리
	 * @param refund
	 * @return
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public List<String> readyRefundToToss(String id) throws IOException, InterruptedException {
		String base64 = "으아악";
		String encodedKey= new String(Base64.getEncoder().encode((base64+":").getBytes(StandardCharsets.UTF_8)));
		
		// order amount 찾기
				Refund refund = refundRepository.selectRefundById(Integer.parseInt(id));
	
				Order order = orderRepository.selectOrderById(refund.getOrderId());
				OrderDetail orderDetail = orderDetailRepository.selectOrderDetailByOrderId(order.orderId);

				HttpRequest request = HttpRequest.newBuilder()
					    .uri(URI.create("https://api.tosspayments.com/v1/payments/"+order.orderId+"/cancel"))
					    .header("Authorization", "우와아앙")
					    .header("Content-Type", "application/json")
					    .method("POST", HttpRequest.BodyPublishers.noBody())
					    .build();
					HttpResponse<String> response11 = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

				Map<String,String> parameters = new HashMap<>();
				parameters.put("paymentKey", order.orderId);
				parameters.put("cancelReason", "고객 변심");
				parameters.put("cancelamount", order.getAmount().toString());
				parameters.put("refundReceiveAccount ", "{bank: '테스트', accountNumber: '테스트계좌번호입니당', holderName: '엄송'}");

				
				// HttpMessage 만들기
				HttpEntity<Map<String,String>> requestEntity = new HttpEntity(parameters,this.getTossHeaders());
				System.out.println(requestEntity);
				
				RestTemplate template = new RestTemplate();
				
				// 환불 요청 url
		        String url = "https://api.tosspayments.com/v1/payments/"+order.orderId+"/cancel";
		        
		        // 응답 객체 생성
		        List<String> response = template.postForObject(url, requestEntity, List.class);
		        
        return response;
	}

	/**
	 * 토스 헤더 생성
	 * @return
	 */
	private org.springframework.http.HttpHeaders getTossHeaders(){
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Authorization", "Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg==");
		headers.set("Content-type", "application/json");
        
        return headers;
	}
	
}
