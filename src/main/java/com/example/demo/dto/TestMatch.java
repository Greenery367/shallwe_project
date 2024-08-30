package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestMatch {

	private String nickname;
	private String uploadFileName;
	private int mbti;
}
