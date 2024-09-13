package com.example.demo.dto;

import lombok.Data;

@Data
public class ReportDTO {

	private int senderId;
	private int recieverId;
	private int typeId;
	private String type;
	private String reason;
}
