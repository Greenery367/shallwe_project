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
public class Amount {
	
	public Integer total;
	public Integer taxFree;
	public Integer vat;
	public Integer point;
	public Integer discount;
	public Integer greenDeposit;

}
