package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterExchangeDTO;
import com.example.demo.repository.RegisterExchangeRepository;

@Service
public class RegisterExchangeService {
	private final RegisterExchangeRepository registerExchangeRepository;
	
	@Autowired
	public RegisterExchangeService(RegisterExchangeRepository registerExchangeRepository) {
		this.registerExchangeRepository = registerExchangeRepository; 
	}
	
	/**
	 * 환전 신청
	 * @param registerExchangeDTO
	 */
	public void registerExchange(RegisterExchangeDTO registerExchangeDTO) {
		registerExchangeRepository.registerExchange(registerExchangeDTO.getUserId(), registerExchangeDTO.getSubmallId(), registerExchangeDTO.getAmount());
	}
	
	/**
	 * 환전 내역 
	 * @param userId
	 * @return
	 */
	public List<RegisterExchangeDTO> getExchangeHistory(Integer userId){
		List<RegisterExchangeDTO> exchangeList = new ArrayList<>();
		exchangeList = registerExchangeRepository.getExchangeHistory(userId);
		
		return exchangeList;
	}
}
