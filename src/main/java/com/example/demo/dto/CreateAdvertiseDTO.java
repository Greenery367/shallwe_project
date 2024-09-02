package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdvertiseDTO {
	
		private Integer id;
		private Integer placeId;
		private String title;
		private String customer;
		private String link;
		private String originFileName;
		private String uploadFileName;
		private String createdAt;
		private String startDate;
		private String endDate;
		private Integer status; 
		
		// Advertise object로 반환
		public Advertise toAdvertise() {
			return Advertise.builder()
				.id(this.id)
				.placeId(this.placeId)
				.title(this.title)
				.customer(this.customer)
				.originFileName(this.uploadFileName)
				.uploadFileName(this.uploadFileName)
				.startDate(this.startDate)
				.endDate(this.endDate)
				.status(this.status)
				.build();
					
		}
	
	

}
