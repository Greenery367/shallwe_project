package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TossPayDTO {
	
	public String responseStr;
	public String method;
	public String orderName;

}
