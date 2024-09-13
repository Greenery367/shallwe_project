package com.example.demo.service;

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
	
	
	public void registerExchange(RegisterExchangeDTO registerExchangeDTO) {
		registerExchangeRepository.registerExchange(registerExchangeDTO.getUserId(), registerExchangeDTO.getSubmallId(), registerExchangeDTO.getAmount());
	}
}
