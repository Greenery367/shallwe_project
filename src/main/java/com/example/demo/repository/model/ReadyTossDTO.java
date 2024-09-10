package com.example.demo.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class ReadyTossDTO {
	public String mId;
	public String lastTransactionKey;
	public String paymentKey;
	public String orderId;
	public String orderName;
	public String taxExemptionAmount;
	public String status;
	public Timestamp requestedAt;
	public Timestamp approvedAt;
	public Boolean useEscrow;
	public Boolean cultureExpense;
	public Card card;
	
}

