package com.example.demo.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedCancelAmount {

	public Integer total;
	public Integer taxFree;
	public Integer vat;
	public Integer point;
	public Integer discount;
	public Integer greenDeposit;
}
