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
public class AdminSelectCommentDTO {
	private Integer id;
	private Integer postId;
	private	String content;
	private Integer authorId;
	private Timestamp createdAt;
}
