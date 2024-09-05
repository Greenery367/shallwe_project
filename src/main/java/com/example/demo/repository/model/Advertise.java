package com.example.demo.repository.model;

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
public class Advertise {
	
	private String filePath; // 파일 경로를 저장할 필드
	private Integer id;
	private Integer placeId;
	private String title;
	private String customer;
	private String link;
	private String originFileName;
	private String uploadFileName;
	private String createdAt;
	private String startDate;
	private String endDate;
	private Integer status; 
	private String someProperty;
	

}
