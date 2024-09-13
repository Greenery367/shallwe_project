package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class ReadyResponseDTO {
	
	private String tid;
	private String next_redirect_pc_url;

}
