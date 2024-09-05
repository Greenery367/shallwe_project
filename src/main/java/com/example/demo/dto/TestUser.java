package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class TestUser {

	private int id;
	private int mbti;
	private String nickname;
	private MultipartFile fileName;
	private String originFileName;
	private String uploadFileName;
	
	public String setUpUserImage() {
		System.out.println("파일 이름 : " + uploadFileName);
		String image = null;
		
		if(originFileName != null && uploadFileName == null) {
			image = originFileName;
		} else {
			image = uploadFileName == null ? 
					"https://picsum.photos/id/1/350" : "/images/uploads/" + uploadFileName;
		}
		return image;
	}
}
