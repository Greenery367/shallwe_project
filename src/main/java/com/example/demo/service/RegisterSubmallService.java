package com.example.demo.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterSubmallDTO;
import com.example.demo.dto.RegisterSubmallForAdminDTO;
import com.example.demo.repository.RegisterSubmallRepositroy;
import com.example.demo.repository.model.RegisterSubmall;

@Service
public class RegisterSubmallService {
	private final RegisterSubmallRepositroy registerSubmallRepositroy;
	
	@Autowired
	public RegisterSubmallService(RegisterSubmallRepositroy registerSubmallRepositroy) {
		this.registerSubmallRepositroy = registerSubmallRepositroy;
	}
	
	// id로 신청 내역 찾기
	public RegisterSubmall selectRegisterSubmallInfoById(int id) {
		return registerSubmallRepositroy.selectRegisterSubmallById(id);
	}
	
	public void registerSubmall(RegisterSubmallForAdminDTO registerSubmallDTO) {
		//registerSubmallRepositroy.registerSubmall(registerSubmallDTO.user.getUserId(), registerSubmallDTO.getBankInfo());
	}
}
