package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LecturePaymentDTO {
	
	private int userId;
	private int classId;
	private long currentCash;
	private long price;
	
	// 잔액 업데이트 메서드
    public void updateCurrentCash(long newCash) {
        this.currentCash = newCash;
    }
}
