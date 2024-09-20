package com.example.demo.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RegisterExchange {
	
	public int id;
	public int userId;
	public String submallId;
	public Long amount;
	public Timestamp createdAt;
	public int status;

}
