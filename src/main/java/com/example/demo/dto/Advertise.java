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
public class Advertise {
	
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

}
