package com.example.demo.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterExchangeDTO {
	
	private int userId;
	private int submallId;
	private long amount;
	private Timestamp createdAt;
	private int status;
	private long lectureCash;
	
}
