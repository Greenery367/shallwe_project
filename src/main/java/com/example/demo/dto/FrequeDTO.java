package com.example.demo.dto;

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
public class FrequeDTO {
	
	private int id;
	private String title;
	private String userId;
	private String writer;
	private String content;
	private int replyStatus;
	private String createdAt;
}
