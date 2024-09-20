package com.example.demo.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.BankVerificationDTO;
import com.example.demo.repository.BankVerificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankVerificationService {

    private final BankVerificationRepository repository;
    private final RestTemplate restTemplate;


    // 외부 API로 계좌 본인 인증 처리
    public BankVerificationDTO verifyAccount(String bankCodeStd, String accountNum, String accountHolderName) {
        // API URL 설정
        String apiUrl = "https://openapi.openbanking.or.kr/v2.0/inquiry/real_name";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json; charset=UTF-8");
        headers.set("Authorization", "Bearer YOUR_ACCESS_TOKEN");  // 실제 인증 토큰으로 교체

        // 요청 바디에 들어갈 데이터 설정
        BankVerificationDTO requestBody = BankVerificationDTO.builder()
                .bankCodeStd(bankCodeStd)
                .accountNum(accountNum)
                .accountHolderName(accountHolderName)
                .build();

        // 요청 엔티티 생성
        HttpEntity<BankVerificationDTO> requestEntity = new HttpEntity<>(requestBody, headers);

        // API 호출 및 응답 받기
        ResponseEntity<BankVerificationDTO> response = restTemplate.postForEntity(apiUrl, requestEntity, BankVerificationDTO.class);

        // 응답 바디 반환
        return response.getBody();
    }

    // 필요한 헤더 설정 (예제에선 사용되지 않음, 다른 메서드에서 필요하면 사용 가능)
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer YOUR_API_KEY");
        return headers;
    }
}
