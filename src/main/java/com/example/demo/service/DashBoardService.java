package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.AdminRepository;

@Service
public class DashBoardService {
	
private final AdminRepository adminRepository;
	
	public DashBoardService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

}
