package com.example.demo.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
	private int userId;
	private int id;
	private int classId;
	private int author_id;
	private String nickName;
	private String comment;
	private int grade;
	private Timestamp createdAt;
}
