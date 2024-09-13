package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TossPayApproveResponseDTO{
	
	public String mld;
	public String lastTransactionKey;
	public String paymentKey;
	public String orderId;
	public String orderName;
	public int taxExemptionAmount;
	public String status;
	public String requestedAt;
	public String approvedAt;
	public String useEscrow;
	public String cultureExpense;
	public String card;
	public String virtualAccount;
	public String transfer;
	public String mobilePhone;
	public String giftCertificate;
	public String cashReceipt;
	public String cashReceipts;
	public String discount;
	public String secret;
	public String type;
	public String easyPay;
	public String country;
	public String failure;
	public String isPartialCancelable;
	public String receipt;
	public CheckoutDTO checkout;
	public String currency;
	public int totalAmount;
	public int balanceAmount;
	public int suppliedAmount;
	public int vat;
	public int taxFreeAmount;
	public String version;
	public String metadata;

}
