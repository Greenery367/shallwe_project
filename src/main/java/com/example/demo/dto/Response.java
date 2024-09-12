package com.example.demo.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.ToString;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@ToString

public class Response {
	private String name;
	private String nickname;
	private String email;
}
