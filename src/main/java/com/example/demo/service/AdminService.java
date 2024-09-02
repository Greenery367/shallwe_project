package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.Advertise;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService {
	
	private final AdminRepository adminRepository;
	
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}
	
	// 유저 수 계산
	public int countUser() {
		
		return adminRepository.countNumberOfUser();
	}
	
	public int countChargeCash() {
		
		return adminRepository.countChargeAmount();
	}
	
	public double countSpendCashRate() {
		
		return adminRepository.countSpendAmountRate();
	}
	
	// 광고 추가
	//public int insertAdvertise() {
		
		// return adminRepository.insertAdvertise();
	// }
	
	// 광고 상태 변경 
	@Transactional
	public int updateAdvertiseStatus() {
		
		return adminRepository.updateAdvertiseStatus();
	}
	
	// 전체 광고 조회
	public List<Advertise> selectAllAdvertise(){
		List<Advertise> advertiseEntity = null;
		
		advertiseEntity = adminRepository.selectAllAdvertise();
		
		return advertiseEntity;
	}

}
