package com.example.demo.repository.model;

import java.sql.Date;
import java.sql.Timestamp;

import com.example.demo.dto.BankInfoDTO;

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
public class User {
	private int userId;
	private String id;
	private String username;
	private String password;
	private String nickname;
	private String birthDate;
	private String email;
	private String phoneNumber;
	private String originFileName;
	private String uploadFileName;
	private int mbti; // 원석 추가
	private long lectureCash;
	private long currentCash;
	private int challengePoint;
	private Timestamp createdAt;
	private String userAccount;
	private int status;
	private BankInfoDTO bankInfo;

	public String setUpUserImage() {
		if(originFileName == null) {
			return "/images/default.png";
		}else {
			if(uploadFileName == null) {
				return originFileName;
			}else {
				return "/images/uploads/"+uploadFileName;
			}
		}
	}
}
