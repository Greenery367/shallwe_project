package com.example.demo.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	
	public int issuerCode;
	public int acquirerCode;
	public String number;
	public int installmentPlanMonths;
	public Boolean isInterestFree;
	public String interestPayer;
	public String approveNo;
	public Boolean useCardPoint;
	public String cardType;
}
