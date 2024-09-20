package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlarmDTO {

	private int id;
	private int userId;
	private int type;
	private int status;
	private String uploadFileName;
	private String nickname;
	private String content;
	
}
