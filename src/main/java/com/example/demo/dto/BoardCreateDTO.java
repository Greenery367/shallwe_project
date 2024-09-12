package com.example.demo.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardCreateDTO {
	private int userId;
	private int id;
	private int categoryId;
	private String title;
	private String content;
	private int authorId;
	private Timestamp createdAt;
	
}
