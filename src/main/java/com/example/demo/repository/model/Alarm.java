package com.example.demo.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alarm {
	
	private int id;
	private int type;
	private int typeId;
	private int userId;
	private int opponentId;
	private int status;
	private String content;
	private Timestamp createdAt;
	
}
