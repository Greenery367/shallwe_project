package com.example.demo.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSubmall {
	
	public int id;
	public int userId;
	public int bankId;
	public Timestamp createdAt;
	public int status;
}
