package com.example.demo.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repository.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SignUpDTO {
	private String id;
	private String password;
	private String username;
	private String nickname;
	private Date birthDate;
	private String email;
	private String phoneNumber;
	private MultipartFile mFile;
	private String originFileName;
	private String uploadFileName;
	
	public User toUser(){
		return User.builder()
				.id(this.id)
				.password(this.password)
				.username(this.username)
				.nickname(this.nickname)
				.birthDate(this.birthDate)
				.email(this.email)
				.phoneNumber(this.phoneNumber)
				.originFileName(this.originFileName)
				.uploadFileName(this.uploadFileName)
				.build();
	}
	
}
