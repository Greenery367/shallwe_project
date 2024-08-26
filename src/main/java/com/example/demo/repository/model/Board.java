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
	
	private int id;
	private int categoryId;
	private String title;
	private String content;
	private int author;
	private int viewNum;
	private int good;
	private Timestamp createdAt;
	
}
