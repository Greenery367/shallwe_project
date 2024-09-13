package com.example.demo.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
	
	public int id;
	public int userId;
	public String orderId;
	public String name;
	public Long amount;
	public Timestamp createdAt;
	public int status;
}
