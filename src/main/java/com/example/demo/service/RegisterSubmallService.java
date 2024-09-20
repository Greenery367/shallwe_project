package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	// 서브몰 신청하기
    public void registerSubmall(Integer userId, Integer bankId) {
        registerSubmallRepositroy.registerSubmall(userId, bankId);
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
	
	// id로 신청 내역 찾기
	public RegisterSubmall selectRegisterSubmallInfoById(int id) {
		return registerSubmallRepositroy.selectRegisterSubmallById(id);
	}
    
	
	public void registerSubmall(RegisterSubmallForAdminDTO registerSubmallDTO) {
		//registerSubmallRepositroy.registerSubmall(registerSubmallDTO.user.getUserId(), registerSubmallDTO.getBankInfo());
	}
}
