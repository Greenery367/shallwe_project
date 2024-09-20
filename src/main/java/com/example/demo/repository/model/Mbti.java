package com.example.demo.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

// MBTI 클래스
public class Mbti {
	private int id;
	private String name;
	private String nickname;
	private String content;

}
