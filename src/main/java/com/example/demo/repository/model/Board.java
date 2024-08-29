package com.example.demo.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	
	private Integer id;
	private Integer categoryId;
	private String title;
	private String content;
	private Integer authorId;
	private Integer viewNum;
	private Integer good;
	private Timestamp createdAt;
	
}
