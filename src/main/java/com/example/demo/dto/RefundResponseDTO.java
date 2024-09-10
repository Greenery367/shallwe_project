package com.example.demo.dto;

import com.example.demo.repository.model.Amount;
import com.example.demo.repository.model.ApprovedCancelAmount;
import com.example.demo.repository.model.CancelAvailableAmount;
import com.example.demo.repository.model.CanceledAmount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundResponseDTO {
	
	public String aid;
	public String tid;
	public String cid;
	public String status;
	public String partnerOrderId;
	public String partnerUserId;
	public String paymentMethodType;
	public Amount amount;
	public ApprovedCancelAmount approvedCancelAmount;
	public CanceledAmount canceledAmount;
	public CancelAvailableAmount cancelAvailableAmount;
	public String itemName;
	public String item_code;
	public Integer quantity;
	public String created_at;
	public String approved_at;
	public String canceled_at;
	public String payload;
}
