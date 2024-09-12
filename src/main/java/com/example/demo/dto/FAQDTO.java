package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class FAQDTO {
<<<<<<< HEAD
	private Integer id;
=======
	private int id;
>>>>>>> ae88f71a5d98cf993b2fdaf0d6538975fc5d4348
	private String title;
	private String userId;
	private String writer;
	private String content;
<<<<<<< HEAD
	private Integer replyStatus;
	private String createdAt;
	private Integer status;
=======
	private int replyStatus;
	private String createdAt;
>>>>>>> ae88f71a5d98cf993b2fdaf0d6538975fc5d4348
}
