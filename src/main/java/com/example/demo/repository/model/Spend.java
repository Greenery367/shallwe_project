package com.example.demo.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Spend {
	private Integer id;
	private Integer userId;
	private Integer classId;
	private Long spend;
	private String createdAt;
}
