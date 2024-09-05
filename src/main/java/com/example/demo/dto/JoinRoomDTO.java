package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JoinRoomDTO {

	private int userId;
	private int roomId;
}
