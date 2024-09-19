package com.example.demo.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Refund {
	public int id;
	public String name;
	public int orderId;
	public int userId;
	public int amount;
	public String reason;
	public Timestamp createdAt;
	public int status;

}
