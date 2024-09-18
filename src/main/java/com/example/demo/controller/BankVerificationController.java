package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.BankInfoDTO;
import com.example.demo.dto.BankVerificationDTO;
import com.example.demo.repository.model.User;
import com.example.demo.service.BankService;
import com.example.demo.service.BankVerificationService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/verify")
public class BankVerificationController {

    private final BankVerificationService service;
    private final HttpSession session;
    private final BankService bankService;

    // BankVerificationService 주입
    public BankVerificationController(BankVerificationService service, HttpSession session, BankService bankService) {
        this.service = service;
        this.session = session;
        this.bankService = bankService;
    }
    
 // 인증 페이지로 이동 (GET 요청)
    @GetMapping("/verifyPage")
    public String showVerificationPage(Model model) {
    	User user = (User) session.getAttribute("principal");
    	List<BankInfoDTO> banks = bankService.getAllBanks(); // 은행 목록 조회
    	System.out.println("은행 목록들 ___ " + banks);
        // 은행 목록 출력 (디버깅용)
        if (banks != null) {
            banks.forEach(bank -> System.out.println("은행: " + bank.getBankId() + " - " + bank.getBankName()));
        } else {
            System.out.println("은행 목록이 비어 있습니다.");
        }
    	
    	model.addAttribute("user", user);
		model.addAttribute("banks", banks); // 모델에 은행 목록 추가
    	
        return "myPage/userExchange/resiterSubmall"; // JSP 파일 경로
    }

    // 계좌 본인 인증 처리
    @PostMapping
    public ResponseEntity<BankVerificationDTO> verifyAccount(
            @RequestParam String bankCodeStd,
            @RequestParam String accountNum,
            @RequestParam String accountHolderName) {
        
        // 서비스 호출하여 계좌 인증 처리
        BankVerificationDTO verificationResult = service.verifyAccount(bankCodeStd, accountNum, accountHolderName);

        // 응답으로 처리 결과 반환
        return ResponseEntity.ok(verificationResult);
    }
}
