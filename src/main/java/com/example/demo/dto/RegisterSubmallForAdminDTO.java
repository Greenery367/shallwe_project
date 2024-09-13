package com.example.demo.dto;

import com.example.demo.repository.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSubmallForAdminDTO {
	
	public User user;
	public RegisterSubmallDTO submallInfo;
	public BankDTO bankInfo;

}
