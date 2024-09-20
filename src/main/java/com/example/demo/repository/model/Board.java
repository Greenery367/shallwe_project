package com.example.demo.repository.model;

import java.sql.Timestamp;

import com.example.demo.utils.Formatter;

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
public class Board {
	
	private Integer id;
	private Integer categoryId;
	private String title;
	private String content;
	private Integer authorId;
	private String nickName;
	private Integer viewNum;
	private Integer good;
	private Timestamp createdAt;
	
}
