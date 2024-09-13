package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterSubmallDTO;
import com.example.demo.dto.RegisterSubmallForAdminDTO;
import com.example.demo.repository.RegisterSubmallRepositroy;

@Service
public class RegisterSubmallService {
	private final RegisterSubmallRepositroy registerSubmallRepositroy;
	
	@Autowired
	public RegisterSubmallService(RegisterSubmallRepositroy registerSubmallRepositroy) {
		this.registerSubmallRepositroy = registerSubmallRepositroy;
	}
	
	public void registerSubmall(RegisterSubmallForAdminDTO registerSubmallDTO) {
		//registerSubmallRepositroy.registerSubmall(registerSubmallDTO.user.getUserId(), registerSubmallDTO.getBankInfo());
	}
	
	 // 서브몰 신청 여부 확인 메서드
    public boolean isSubmallRegistered(Integer userId) {
        int count = registerSubmallRepositroy.checkSubmallExists(userId);
        return count > 0;
    }
	
}
