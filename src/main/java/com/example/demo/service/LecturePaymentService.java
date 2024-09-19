package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LecturePaymentDTO;
import com.example.demo.repository.LecturePaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LecturePaymentService {

    private final LecturePaymentRepository paymentRepository;
    
    @Transactional
    public String processPayment(LecturePaymentDTO paymentDTO) {
        // 사용자의 현재 캐시 조회
        long currentCash = paymentRepository.getCurrentCash(paymentDTO.getUserId());

        // 잔액이 결제 금액보다 적은지 확인
        if (currentCash < paymentDTO.getPrice()) {
            return "잔액이 부족하여 결제를 진행할 수 없습니다.";
        }
        
        // 강사의 ID를 classId로 조회
        Integer lecturerId = paymentRepository.getLecturerId(paymentDTO.getClassId());

        if (lecturerId == null) {
            return "강사 정보를 찾을 수 없습니다.";
        }

        // 수강자 캐시 차감
        paymentRepository.paymentUserCash(paymentDTO.getUserId(), paymentDTO.getPrice());

        // 강사 캐시 증가
        paymentRepository.paymentLectureCash(paymentDTO.getClassId(), paymentDTO.getPrice());

        // 결제 내역 기록
        paymentRepository.createSpend(paymentDTO);

        return "success";
    }
}

