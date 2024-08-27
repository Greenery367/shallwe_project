package com.example.demo.repository.model;

import java.sql.Date;
import java.sql.Timestamp;

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
	private String id;
	private String username;
	private String password;
	private String nickname;
	private Date birthDate;
	private String email;
	private String phoneNumber;
	private String originFileName;
	private String uploadFileName;
	private int currentCash;
	private int challengePoint;
	private Timestamp createdAt;
	private int status;

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
