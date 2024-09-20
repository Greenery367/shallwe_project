package com.example.demo.repository.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Report {

	private int id;
	private String type;
	private int typeId;
	private String reason;
	private int senderId;
	private int recieverId;
	private Timestamp createdAt;
	
}
