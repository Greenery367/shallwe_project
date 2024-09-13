package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlarmDTO {

	private int sendUserId;
	private int receiveUserId;
	private String uploadFileName;
	private String nickname;
	private String content;
	
}
