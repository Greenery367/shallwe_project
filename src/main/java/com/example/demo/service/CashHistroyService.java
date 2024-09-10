package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CashHistoryRepository;
import com.example.demo.repository.model.CashHistory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CashHistroyService {

	@Autowired
	private CashHistoryRepository cashHistoryRepository;
	
	// 모든 
	public List<CashHistory> getAllCashHistroy() {
		List<CashHistory> allHistory = cashHistoryRepository.selectAllCashHistory();
		return null;
	}

}
