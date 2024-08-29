package com.example.demo.dto;

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
	
	private Integer userId;
	private Integer roleId;
	private String username;
	private String password;
	private String nickname;
	private String birthDate;
	private String email;
	private String phoneNumber;
	private String originFileName;
	private String uploadFileName;
	private Integer currentCash;
	private Integer challengePoint;
	private Timestamp createdAt;
	private Integer status;
	
}
