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
public class Lecture {

	private int id;
	private int categoryId;
	private int authorId;
	private String title;
	private String subtitle;
	private String content;
	private int limitNum;
	private int currentNum;
	private long price;
	private int totalNum;
	private Timestamp createdAt;
	private String nickName;
	private int status;
	
		
}
