package com.example.demo.dto;

import java.sql.Timestamp;

import com.example.demo.repository.model.User;

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
public class RegisterSubmallDTO {
	
	public int id;
	public User user;
	public String bankName;
	public String bankAccount;
	public Timestamp createdAt;
	public int status;

}
