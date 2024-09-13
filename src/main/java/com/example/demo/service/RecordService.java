package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RecordDTO;
import com.example.demo.repository.RecordRepository;

@Service
public class RecordService {
	private final RecordRepository recordRepository;
	
	@Autowired
	public RecordService(RecordRepository recordRepository) {
		this.recordRepository = recordRepository;
	}
	
	public List<RecordDTO> getChargeBoard(Integer userId) {
		List<RecordDTO> chargeBoardList = new ArrayList<>();
		
		chargeBoardList = recordRepository.ChargeFindById(userId);
		
		return chargeBoardList;
	}
	
	public List<RecordDTO> getExchangeBoard(Integer userId) {
		List<RecordDTO> exchangeBoardList = new ArrayList<>();
		
		exchangeBoardList = recordRepository.ExchangeFindById(userId);
		
		return exchangeBoardList;
	}
	
	public List<RecordDTO> getSpendBoard(Integer userId) {
		List<RecordDTO> spendBoardList = new ArrayList<>();
		
		spendBoardList = recordRepository.SpendFindById(userId);
		
		return spendBoardList;
	}
	
	public List<RecordDTO> getRefundBoard(Integer userId) {
		List<RecordDTO> refundBoardList = new ArrayList<>();
		
		refundBoardList = recordRepository.RefundFindById(userId);
		
		return refundBoardList;
	}
	
}

