package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterSubmallDTO;
import com.example.demo.dto.RegisterSubmallForAdminDTO;
import com.example.demo.repository.RegisterSubmallRepository;
import com.example.demo.repository.model.RegisterSubmall;
import com.example.demo.repository.model.Submall;

@Service
public class RegisterSubmallService {
	private final RegisterSubmallRepository registerSubmallRepositroy;
	
	@Autowired
	public RegisterSubmallService(RegisterSubmallRepository registerSubmallRepositroy) {
		this.registerSubmallRepositroy = registerSubmallRepositroy;
	}
	
	public void registerSubmall(RegisterSubmallForAdminDTO registerSubmallDTO) {
		//registerSubmallRepositroy.registerSubmall(registerSubmallDTO.user.getUserId(), registerSubmallDTO.getBankInfo());
	}
	
	// 서브몰 신청하기
	public void requestSubmall(RegisterSubmallDTO registerSubmallDTO) {
		registerSubmallRepositroy.registerSubmall(registerSubmallDTO.getUserId(), registerSubmallDTO.getBankId());
	}
	
	 // 서브몰 신청 여부 확인 메서드
    public boolean isSubmallRegistered(Integer userId) {
        int count = registerSubmallRepositroy.checkSubmallExists(userId);
        return count > 0;
    }
    
    // 서브몰 id 여부 확인 메서드
    public Submall isSubmallId(Integer userId) {
    	return registerSubmallRepositroy.submallFindById(userId);
    	
    }
	
}
