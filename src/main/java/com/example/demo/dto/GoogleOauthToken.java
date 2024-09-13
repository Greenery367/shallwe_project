package com.example.demo.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// JSON 형식의 코딩 컨벤션 스네이크 케이스를 카멜 노테이션으로 할당.
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GoogleOauthToken {
	
	private String accessToken;
	private Integer expiresIn;
	private String scope;
    private String tokenType;
    private String idToken;
    
}
