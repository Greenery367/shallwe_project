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

public class OrderDetail {
	
	public String orderId;
	public String cid;
	public String tid;
	public String partnerOrderId;
	public String partnerUserId;
	public String itemName;
	public int totalAmount;
	public int taxFreeAmount;

}
