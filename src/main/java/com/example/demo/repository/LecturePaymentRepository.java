package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.LecturePaymentDTO;

@Mapper
public interface LecturePaymentRepository {
	
	  // 결제 시 수강자 캐시 차감
    void paymentUserCash(@Param("userId") int userId, @Param("price") long price);

    // 결제 시 강사 캐시 증가
    void paymentLectureCash(@Param("userId") int userId, @Param("price") long price);

    // 결제 내역 기록
    void createSpend(LecturePaymentDTO paymentDTO);

    // 사용자의 현재 캐시 조회
    long getCurrentCash(@Param("userId") int userId);
    
    // 강사의 ID를 classId로부터 조회
    Integer getLecturerId(@Param("classId") int classId);
    
}
	
