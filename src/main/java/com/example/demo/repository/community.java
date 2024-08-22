package com.example.demo.repository;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class community {
	
	private int id;
	private int categoryId;
	private String title;
	private int author;
	private int veiwNum;
	private int good;
	private Timestamp createdAt;
	
}
