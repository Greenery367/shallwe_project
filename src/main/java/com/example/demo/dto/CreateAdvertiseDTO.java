package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
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
				.placeId(this.placeId)
				.title(this.title)
				.customer(this.customer)
				.link(this.link)
				.startDate(this.startDate)
				.endDate(this.endDate)
				.status(this.status)
				.build();
					
		}
	
	

}
