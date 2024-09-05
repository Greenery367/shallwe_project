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
public class Comment {
	private Integer id;
	private Integer postId;
	private String content;
	private Integer authorId;
	private String nickName;
	private Timestamp createdAt;
}
