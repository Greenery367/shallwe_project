package com.example.demo.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Admin {
	
	private Integer id;
	private Integer roleId;
	private String adminName;
	private String adminId;
	private String password;
	private String email;
	private String phoneNumber;
	private String originFileName;
	private String uploadFileName;
	

}
