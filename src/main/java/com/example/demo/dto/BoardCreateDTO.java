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
	
	private int id;
	private int categoryId;
	private String title;
	private int author;
	private int veiwNum;
	private int good;
	private Timestamp createdAt;
	
}
