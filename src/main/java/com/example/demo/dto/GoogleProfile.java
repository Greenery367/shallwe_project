package com.example.demo.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.ToString;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@ToString

public class GoogleProfile {
	
	private String id; 
	private String name; 
	private String givenName;
	private String familyName;
	private String picture;
	private String locale;
	
}
