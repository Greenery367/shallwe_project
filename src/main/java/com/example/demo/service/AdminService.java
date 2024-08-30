package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.Advertise;
import com.example.demo.dto.CreateAdvertiseDTO;
import com.example.demo.dto.FileUploadAdvertiseDTO;
import com.example.demo.repository.AdminRepository;
import com.example.demo.utils.Define;

@Service
public class AdminService {

    @Autowired
    private final AdminRepository adminRepository;
    
	@Value("${file.upload-dir}")
	private String uploadDir;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // 유저 수 계산
    public int countUser() {
        return adminRepository.countNumberOfUser();
    }

    // 전체 유저 충전 캐쉬
    public int countChargeCash() {
        return adminRepository.countChargeAmount();
    }

    // 캐쉬 사용률
    public double countSpendCashRate() {
        return adminRepository.countSpendAmountRate();
    }

    // 광고 추가
    @Transactional
    public void insertAdvertise(CreateAdvertiseDTO dto) {
        adminRepository.insertAdvertise(dto);
    }

    // 광고 수정
    @Transactional
    public void updateAdvertise(Advertise advertise) {
        adminRepository.updateAdvertise(advertise);
    }

    // 광고 삭제
    @Transactional
    public void deleteAdvertise(Advertise advertise) {
        adminRepository.deleteAdvertiseById(advertise.getId());
    }

    // 전체 광고 조회
    public List<Advertise> selectAllAdvertise() {
        return adminRepository.selectAllAdvertise();
    }

}
