package com.example.demo.dto;

import java.sql.Timestamp;

import com.example.demo.repository.model.Refund;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RefundDTO {
	public int id;
	public int orderId;
	public int userId;
	public Timestamp createdAt;
	public int status;
	public int platform;

}
