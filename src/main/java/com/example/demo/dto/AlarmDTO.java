package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmDTO {

	private int id;
	private int userId;
	private int type;
	private int status;
	private String uploadFileName;
	private String nickname;
	private String content;
	
}
