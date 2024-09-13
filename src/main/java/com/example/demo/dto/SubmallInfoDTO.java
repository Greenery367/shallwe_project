package com.example.demo.dto;

import java.sql.Timestamp;

import com.example.demo.repository.model.RegisterSubmall;
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

public class SubmallInfoDTO {
	
	public RegisterSubmall submallInfo;
	public User user;
	public BankDTO bankInfo;
}
