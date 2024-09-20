package com.example.demo.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashChargeGraphVO {
	
	private int id;
	private int amount;
	private Timestamp createdAt;
	private int status;

}
