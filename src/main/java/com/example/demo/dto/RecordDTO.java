package com.example.demo.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordDTO {
	private Integer id;
	private Integer userId;
	private String classTitle;
	private String teacherNickname;
	private int classPrice;
	private long charge;
	private long spend;
	private long refund;
	private long exchange;
	private int refundStatus;
	private int exchangeStatus;
	private Timestamp createdAt;
	
}
