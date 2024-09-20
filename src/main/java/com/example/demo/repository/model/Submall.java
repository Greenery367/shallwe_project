package com.example.demo.repository.model;

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

public class Submall {
	
	public int userId;
	public String submallId;
	public String type;
	public String status;
	public String accountId;
	public String email;
	public String phoneNumber;

}
