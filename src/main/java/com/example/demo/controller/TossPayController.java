package com.example.demo.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

@RestController
public class TossPayController {

    @GetMapping("/success")
    @ResponseBody
    public String resultTossPay(@RequestParam("orderId") String orderId,
                                @RequestParam("paymentKey") String paymentKey,
                                @RequestParam("amount") Long amount) throws IOException, InterruptedException, URISyntaxException {
        String secretKey = "test_ck_ALnQvDd2VJzdqzNAkgNYVMj7X41m";
        String baseUrl = "https://api.tosspayments.com/v1/payments/" + paymentKey;

        // Basic Authentication을 위한 secretKey 인코딩
        String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes("UTF-8"));

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 엔티티 설정 (본문 없음)
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // RestTemplate 인스턴스 생성
        RestTemplate restTemplate = new RestTemplate();

        // URI 생성
        URI uri = new URI(baseUrl);

        // GET 요청 수행
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        System.out.println(responseEntity.getBody());
        // 응답 본문 반환
        return responseEntity.getBody();
    }
}
