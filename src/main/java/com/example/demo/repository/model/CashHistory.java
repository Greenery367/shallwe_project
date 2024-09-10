package com.example.demo.repository.model;

import java.sql.Timestamp;

// 캐쉬 사용 내역
public class CashHistory {
	
	private int id;
	private int userid;
	private Long charge;
	private Long spend;
	private Long refund;
	private Long exchange;
	private int refundStatus;
	private int exchangeStatus;
	private Timestamp createdAt;
}
